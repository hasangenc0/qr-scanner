package com.hasan.qrScanner.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hasan.qrScanner.R
import com.google.zxing.integration.android.IntentIntegrator
import android.content.Intent
import android.widget.*
import androidx.fragment.app.Fragment

class ConfirmFragment : Fragment() {
    private var codeContent: String = ""
    private var codeFormat: String = ""
    private lateinit var loading: View
    private var scannedPnrs = ArrayList<String>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val scanButton = view.findViewById<View>(R.id.btn_scan_confirm)
        loading = view.findViewById<ProgressBar>(R.id.loading)

        scanButton.setOnClickListener {
            scan()
        }

        val clear_button = view.findViewById<Button>(R.id.btn_scan_confirm_clear)
        clear_button.setOnClickListener {
            val table = view.findViewById<TableLayout>(R.id.infinite_tab_table)
            table.removeAllViews()
            scannedPnrs.clear()

        }
    }

    private fun scan() {
        val integrator = IntentIntegrator.forSupportFragment(this)

        // use forSupportFragment or forFragment method to use fragments instead of activity
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt(this.getString(R.string.scan_bar_code))
        integrator.setCameraId(0)  // Use a specific camera of the device
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_infinite, container, false)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        //retrieve scan result
        val scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        val parentActivity = this.activity as ScanResultReceiver

        if (scanningResult != null) {
            //we have a result
            codeContent = scanningResult.contents
            codeFormat = scanningResult.formatName
            // send received data
            parentActivity.updateUiWithScannedQrData(codeContent, R.id.infinite_tab_table, view, context)
            scan()

        }
    }

}// Required empty public constructor