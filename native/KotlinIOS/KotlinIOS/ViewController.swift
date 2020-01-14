import UIKit
import SharedCode
import Alamofire
import SwiftyJSON

class ViewController: UIViewController, BasePresentCallBack, Webservice, Log {
     
    func log(error: KotlinThrowable) {
        print(error)
    }
    
    func getTodo(todoId: Int32) -> TodoModel {
        var toDo: TodoModel = TodoModel.init(id: 3, title: "Fail", completed: false)
        let request = AF.request("https://jsonplaceholder.typicode.com/todos/1")
        let semaphore = DispatchSemaphore(value: 0)
        request
             .validate(statusCode: 200...302)
             .responseJSON(queue: DispatchQueue.global(qos: .default)){ response in
                print(response)
                //to get status code
                if let status = response.response?.statusCode {
                switch(status){
                    case 200:
                    print("example success")
                    default:
                    print("error with response status: \(status)")
                    }
                }
                //to get JSON return value
                do{
                    let json = try? JSON(data: response.data!)
                    print(json)
                    let id = json?["id"].stringValue
                    toDo = TodoModel.init(
                        id: (json?["id"].int32Value)!,
                        title: ((json?["title"].stringValue)!),
                        completed: (json?["completed"].boolValue)!)
                    print("check todo")
                    print(toDo)
                } catch{
                    print("parse to do error")
                    }
                semaphore.signal()
                }
        _ = semaphore.wait(timeout:DispatchTime.distantFuture)
        return toDo;
    }
    
    func onFail(throwable: KotlinThrowable) {
      print("Fail")
    }
    
    func onSuccess(expectedResult: Any?) {
        print("Sucess")
        print(expectedResult)
    }
    
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
         print("begin project")
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: 300, height: 21))
        label.center = CGPoint(x: 160, y: 285)
        label.textAlignment = .center
        label.font = label.font.withSize(25)
        label.text = CommonKt.createApplicationScreenMessage()
        view.addSubview(label)
        let reposiroty:ToDoRepository =
            RemoteToDoRepositoryImpl(service: self, exception: BaseExceptionImpl(),log: self)
        let todoUseCase: ToDoUseCase = ToDoUseCaseImpl.init(bankRepository: reposiroty)
        todoUseCase.buildUseCase(param: 1)
        todoUseCase.execute(callback: self)
    }
    
    
}



class BaseExceptionImpl: ExceptionMapper{
    func transform(input_ input: KotlinThrowable?)-> BaseException{
        return BaseException()
    }
}

