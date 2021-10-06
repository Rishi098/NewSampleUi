package com.itamazons.pdfdocument

import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.LinearLayout
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.util.Log
import android.view.WindowManager
import android.util.DisplayMetrics
import android.graphics.pdf.PdfDocument.PageInfo
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.graphics.pdf.PdfDocument
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.widget.Toast

class ScrollActivity : AppCompatActivity() {
    private var btn: Button? = null
    private var llScroll: LinearLayout? = null
    private var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)
        btn = findViewById(R.id.btn)
        llScroll = findViewById(R.id.llScroll)
        btn!!.setOnClickListener {
            Log.d("size", " " + llScroll!!.getWidth() + "  " + llScroll!!.getWidth())
            bitmap = loadBitmapFromView(llScroll, llScroll!!.getWidth(), llScroll!!.getHeight())
            createPdf()
        }
    }

    private fun createPdf() {
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        //  Display display = wm.getDefaultDisplay();
        val displaymetrics = DisplayMetrics()
        this.windowManager.defaultDisplay.getMetrics(displaymetrics)
        val hight = displaymetrics.heightPixels.toFloat()
        val width = displaymetrics.widthPixels.toFloat()
        val convertHighet = hight.toInt()
        val convertWidth = width.toInt()

//        Resources mResources = getResources();
//        Bitmap bitmap = BitmapFactory.decodeResource(mResources, R.drawable.screenshot);
        val document = PdfDocument()
        val pageInfo = PageInfo.Builder(convertWidth, convertHighet, 1).create()
        val page = document.startPage(pageInfo)
        val canvas = page.canvas
        val paint = Paint()
        canvas.drawPaint(paint)
        bitmap = Bitmap.createScaledBitmap(bitmap!!, convertWidth, convertHighet, true)
        paint.color = Color.BLUE
        canvas.drawBitmap(bitmap!!, 0f, 0f, null)
        document.finishPage(page)

        // write the document content
        val targetPdf = "/sdcard/pdffromScroll.pdf"
        val filePath: File
        filePath = File(targetPdf)
        try {
            document.writeTo(FileOutputStream(filePath))
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(this, "Something wrong: $e", Toast.LENGTH_LONG).show()
        }

        // close the document
        document.close()
        Toast.makeText(this, "PDF of Scroll is created!!!", Toast.LENGTH_SHORT).show()

        //  openGeneratedPDF();
    }
    //    private void openGeneratedPDF(){

    //        File file = new File("/sdcard/pdffromScroll.pdf");
    //        if (file.exists())
    //        {
    //            Intent intent=new Intent(Intent.ACTION_VIEW);
    //            Uri uri = Uri.fromFile(file);
    //            intent.setDataAndType(uri, "application/pdf");
    //            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    //
    //            try
    //            {
    //                startActivity(intent);
    //            }
    //            catch(ActivityNotFoundException e)
    //            {
    //                Toast.makeText(this, "No Application available to view pdf", Toast.LENGTH_LONG).show();
    //            }
    //        }
    //    }


    companion object {
        fun loadBitmapFromView(v: View?, width: Int, height: Int): Bitmap {
            val b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            val c = Canvas(b)
            v!!.draw(c)
            return b
        }
    }
}