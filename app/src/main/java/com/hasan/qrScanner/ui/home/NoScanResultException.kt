package com.hasan.qrScanner.ui.home

class NoScanResultException : Exception {
    constructor() {}
    constructor(msg: String) : super(msg) {}
    constructor(cause: Throwable) : super(cause) {}
    constructor(msg: String, cause: Throwable) : super(msg, cause) {}
}