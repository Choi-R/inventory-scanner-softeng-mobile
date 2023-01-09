package com.example.inventoryscanner.qrscanner.fragments

import android.app.Activity
import android.content.Intent
import android.hardware.camera2.CameraCaptureSession.CaptureCallback
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.inventoryscanner.R
import com.example.inventoryscanner.qrscanner.MainActivity
import com.example.inventoryscanner.qrscanner.SendMessage
import com.google.rpc.Code
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


class ScannerFragment : Fragment(), EasyPermissions.PermissionCallbacks, EasyPermissions.RationaleCallbacks  {
    var btnScan: Button?=null
    var btnEnterScan: Button?=null
    var code: TextView? = null
    private lateinit var sm :SendMessage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_scanner  , container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        sm = activity as SendMessage
        btnScan= getView()?.findViewById<Button>(R.id.btnScan)
        btnScan!!.setOnClickListener(){
            cameraTask()

            /*sm.sendData("unused")
            val result = "-NK7obD6JZ-WXeqry_aN"
            setFragmentResult("requestKey", bundleOf("data" to result))
            */
        }
    }
    private fun hasCameraAccess():Boolean{
        return EasyPermissions.hasPermissions(requireContext(),android.Manifest.permission.CAMERA)
    }
    private fun cameraTask(){
        if(hasCameraAccess()){
            var qrScanner= IntentIntegrator.forSupportFragment(this)
            qrScanner.setPrompt("Scan a QR Code")
            qrScanner.setCameraId(0)
            qrScanner.setOrientationLocked(true)
            qrScanner.setBeepEnabled(true)
            qrScanner.captureActivity=CaptureActivity::class.java
            qrScanner.initiateScan()

        }
        else{
            EasyPermissions.requestPermissions(
                this,
                "This app needs access to your camera to take pics.",
                123,
                android.Manifest.permission.CAMERA
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data)
        if (result!=null){
            if(result.contents== null){
                Toast.makeText(this.context, "Result Not Found", Toast.LENGTH_SHORT).show()

            }
            else{
                try {
                    sm.sendData("unused")
                    setFragmentResult("requestKey", bundleOf("data" to result.contents.toString()))
                }catch (exception:JSONException){
                    Toast.makeText(this.context,exception.localizedMessage, Toast.LENGTH_SHORT).show()
                    code!!.setText("Item doesnt exist")
                }
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data)
        }

        if(requestCode== AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE){
            Toast.makeText(this.context,"Permission Granted",Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode,permissions,grantResults,this    )

    }
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if(EasyPermissions.somePermissionPermanentlyDenied(this,perms)){
            AppSettingsDialog.Builder(this).build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
    }

    override fun onRationaleDenied(requestCode: Int) {
    }


}