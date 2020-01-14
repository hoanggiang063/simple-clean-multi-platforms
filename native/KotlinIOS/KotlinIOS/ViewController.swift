import UIKit
import SharedCode
import Alamofire
import SwiftyJSON

class ViewController: UIViewController, BasePresentCallBack, Log {
     
    func log(error: KotlinThrowable) {
        print(error)
    }

    func onFail(throwable: KotlinThrowable) {
      print("Fail")
    }
    
    func onSuccess(expectedResult: Any?) {
        print("Sucess to Giang")
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
        
        let webservice:Webservice = WebServiceImpl.init(clientEngine:PlatformService.init().httpClientEngine)
        let reposiroty:ToDoRepository =
            RemoteToDoRepositoryImpl(service: webservice, exception: BaseExceptionImpl(),log: self)
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

