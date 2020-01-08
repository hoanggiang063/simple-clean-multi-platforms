package com.architecture.business.demo.repository

import com.architecture.business.core.repository.BaseRepository
import com.architecture.business.demo.info.ToDoInfo

interface ToDoRepository : BaseRepository<Int, ToDoInfo> {
}