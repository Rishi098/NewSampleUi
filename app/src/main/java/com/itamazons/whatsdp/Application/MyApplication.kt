package com.itamazons.innstatracker.Application

import android.app.Activity
import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.media.MediaScannerConnection
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.FileProvider
import com.github.ybq.android.spinkit.SpinKitView
import com.github.ybq.android.spinkit.style.CubeGrid
import com.google.android.material.snackbar.Snackbar
import com.itamazons.innstatracker.BuildConfig
import com.itamazons.innstatracker.R
import com.itamazons.innstatracker.SharePreferene.SharePref
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.channels.FileChannel
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        SharePref.instance?.InitizeSharePref(this)
        setDefaultSharePref()
    }


    companion object {
        lateinit var instance: MyApplication
            private set
    }


    public fun stopDialog() {
        if (dialogspinner != null) {
            dialogspinner!!.dismiss()
            dialogspinner = null
        }
    }


    public fun isImage(imagePath: String?): Boolean {
        val okFileExtensions = arrayOf("jpg", "png", "gif", "jpeg")
        val file = File(imagePath)
        for (extension in okFileExtensions) {
            if (file.name.toLowerCase().endsWith(extension)) {
                return true
            }
        }
        return false
    }


    fun getPictureUri(filepath: String?): Uri? {
        val file = File(filepath)
        var uri: Uri? = null
        uri = if (Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID.toString() + ".provider",
                file
            )
        } else {
            Uri.fromFile(file)
        }
        return uri
    }

    fun prettyCount(number: Number): String? {
        val suffix = charArrayOf(' ', 'k', 'M', 'B', 'T', 'P', 'E')
        val numValue = number.toLong()
        val value = Math.floor(Math.log10(numValue.toDouble())).toInt()
        val base = value / 3
        return if (value >= 3 && base < suffix.size) {
            DecimalFormat("#0.0").format(
                numValue / Math.pow(
                    10.0,
                    (base * 3).toDouble()
                )
            ) + suffix[base]
        } else {
            DecimalFormat("#,##0").format(numValue)
        }
    }

    fun getVideoUri(filePath: String?): Uri? {
        val file = File(filePath)
        var uri: Uri? = null
        uri = if (Build.VERSION.SDK_INT >= 24) {
            FileProvider.getUriForFile(
                this,
                BuildConfig.APPLICATION_ID.toString() + ".provider",
                file
            )
        } else {
            Uri.fromFile(file)
        }
        return uri
    }

    fun isFileAvailable(filename: String): Boolean {
        val root = Environment.getExternalStorageDirectory().absolutePath + "/"
        val destinationFile = File(
            root + MyApplication.instance.getString(R.string.app_name)
                .toString() + File.separator.toString() + filename
        )
        return if (destinationFile.exists()) true else false
    }

    var dialogspinner: Dialog? = null

    public fun startDialog(context: Context?) {
        try {
            if (context != null && !(context as Activity).isFinishing) {
                if (dialogspinner != null) {
                    dialogspinner!!.dismiss()
                }
                dialogspinner = Dialog(context, R.style.startdialog)
                dialogspinner!!.setContentView(R.layout.dialog_loading)
                val spinKitView =
                    dialogspinner!!.findViewById<View>(R.id.spin_kit) as SpinKitView
                val wave = CubeGrid()
                wave.color = context.getResources().getColor(R.color.buttontext)
                spinKitView.setIndeterminateDrawable(wave)
                dialogspinner!!.window!!.attributes.width =
                    ViewGroup.LayoutParams.MATCH_PARENT
                dialogspinner!!.window!!.attributes.height =
                    ViewGroup.LayoutParams.MATCH_PARENT
                dialogspinner!!.setCancelable(false)
                dialogspinner!!.show()
            }
        } catch (e: java.lang.Exception) {
        }
    }

    private var snackbar: Snackbar? = null

    fun showSnackbar(
        context: Context,
        parent: View,
        msg: String
    ) {
        snackbar = Snackbar.make(parent, msg, Snackbar.LENGTH_LONG)
        snackbar!!.getView().setBackgroundColor(context.resources.getColor(R.color.colorPrimary))
        val view: View = snackbar!!.getView()
        val tv =
            view.findViewById<View>(com.google.android.material.R.id.snackbar_text) as TextView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) tv.textAlignment =
            View.TEXT_ALIGNMENT_CENTER else tv.gravity =
            Gravity.CENTER_HORIZONTAL
        tv.setTextColor(Color.WHITE)
        snackbar!!.show()
    }

    fun deleteLocationFile(filename: String) {
        try {
            val root = Environment.getExternalStorageDirectory().absolutePath + "/"
            val destinationFile = File(
                root + "Istahub/Post/Images" + File.separator.toString() + filename
            )
            if (destinationFile.exists()) destinationFile.delete()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun saveFile(context: Context?, filePath: String?) {
        try {
            val root = Environment.getExternalStorageDirectory().absolutePath + "/"
            val createDir = File(
                root + instance.getString(R.string.app_name)
                    .toString() + File.separator
            )
            if (!createDir.exists()) {
                createDir.mkdir()
            }
            val sourceFile = File(filePath)
            val destinationFile = File(
                root + instance.getString(R.string.app_name)
                    .toString() + File.separator.toString() + sourceFile.absolutePath.substring(
                    sourceFile.absolutePath.lastIndexOf("/") + 1
                )
            )
            if (destinationFile.exists()) return
            destinationFile.createNewFile()
            var source: FileChannel? = null
            var destination: FileChannel? = null
            try {
                source = FileInputStream(sourceFile).channel
                destination = FileOutputStream(destinationFile).channel
                destination.transferFrom(source, 0, source.size())
            } finally {
                source?.close()
                destination?.close()
            }
            MediaScannerConnection.scanFile(
                context, arrayOf(destination.toString()), null
            ) { path, uri ->
                //                            Log.i("ExternalStorage", "Scanned " + path + ":");
                //                            Log.i("ExternalStorage", "-> uri=" + uri);
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }


    /**
     * Checking for all possible internet providers
     */
    fun IsInternetAvailable(): Boolean {
        val connectivity =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivity != null) {
            val info = connectivity.allNetworkInfo
            if (info != null) for (i in info.indices) if (info[i]
                    .state == NetworkInfo.State.CONNECTED
            ) {
                return true
            }
        }
        return false
    }

    public fun getDate(date: Long): String? {
        try {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val netDate = Date(date)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    public fun getDDDate(date: Long): String? {
        try {
            val sdf = SimpleDateFormat("dd-MM-yyyy")
            val netDate = Date(date)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }

    public fun getTimeStamp(str_date: String): Long? {
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        val date = formatter.parse(str_date) as Date
        return date.time
    }

    public fun getRevertDate(str_date: String): String? {
        return str_date.split("-")[2] + "-" + str_date.split("-")[1] + "-" + str_date.split("-")[0]
    }

    val ISDEBUG = true

    // This is common method for Print Log Information message
    fun PrintLogInfo(activityname: String, message: String?) {
        if (message == null) {
            return
        }
        if (ISDEBUG) Log.i("API:$activityname", message)
    }

    // This is common method for Print Log warning message
    fun PrintLogWarning(activityname: String?, message: String?) {
        if (ISDEBUG) Log.w(activityname, message.toString())
    }

    // This is common method for Print Log error message
    fun PrintLogError(activityname: String?, message: String?) {
        if (ISDEBUG) Log.e(activityname, message.toString())
    }
}
