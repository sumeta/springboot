package com.github.sumeta.springboot.webflux.webclient.utils

import com.github.sumeta.springboot.webflux.webclient.utils.ErrorCode.SYSTEM_ERROR


class ClientException : RuntimeException {
    var statusCd: String = ""
    var statusDesc: String = ""

    constructor() : super() {
        this.statusCd = SYSTEM_ERROR
        this.statusDesc = message.orEmpty()
    }

    constructor(statusDesc: String) : this() {
        this.statusCd = SYSTEM_ERROR
        this.statusDesc = statusDesc
    }


}
