package com.hasan.qrScanner.ui.home

import android.content.Context
import android.view.View

interface ScanResultReceiver {
    fun updateUiWithScannedQrData(qr: String, tableId: Int, view: View?, context: Context?)
}