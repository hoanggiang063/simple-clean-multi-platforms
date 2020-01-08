package com.architecture.business.core.exception

class BusinessException : BaseException() {
    var businessCode: String? = null
    var businessMessage: String? = null;
}