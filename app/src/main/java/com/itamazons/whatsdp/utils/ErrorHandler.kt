package com.itamazons.whatsdp.utils

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Utility class for handling errors and logging throughout the application
 */
object ErrorHandler {
    
    private const val TAG = "ErrorHandler"
    
    /**
     * Logs an error with appropriate tag and shows a user-friendly toast
     * @param context Application context
     * @param tag Log tag for identification
     * @param message Error message to log
     * @param throwable Optional throwable for stack trace
     * @param showToast Whether to show toast to user
     */
    fun handleError(
        context: Context?,
        tag: String,
        message: String,
        throwable: Throwable? = null,
        showToast: Boolean = true
    ) {
        // Log the error
        if (throwable != null) {
            Log.e(tag, message, throwable)
        } else {
            Log.e(tag, message)
        }
        
        // Show toast if requested and context is available
        if (showToast && context != null) {
            try {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e(TAG, "Failed to show error toast", e)
            }
        }
    }
    
    /**
     * Logs an error with string resource
     * @param context Application context
     * @param tag Log tag for identification
     * @param messageRes String resource for error message
     * @param throwable Optional throwable for stack trace
     * @param showToast Whether to show toast to user
     */
    fun handleError(
        context: Context?,
        tag: String,
        @StringRes messageRes: Int,
        throwable: Throwable? = null,
        showToast: Boolean = true
    ) {
        val message = context?.getString(messageRes) ?: "Unknown error"
        handleError(context, tag, message, throwable, showToast)
    }
    
    /**
     * Logs a warning message
     * @param tag Log tag for identification
     * @param message Warning message
     * @param throwable Optional throwable for stack trace
     */
    fun logWarning(tag: String, message: String, throwable: Throwable? = null) {
        if (throwable != null) {
            Log.w(tag, message, throwable)
        } else {
            Log.w(tag, message)
        }
    }
    
    /**
     * Logs an info message
     * @param tag Log tag for identification
     * @param message Info message
     */
    fun logInfo(tag: String, message: String) {
        Log.i(tag, message)
    }
    
    /**
     * Logs a debug message
     * @param tag Log tag for identification
     * @param message Debug message
     */
    fun logDebug(tag: String, message: String) {
        Log.d(tag, message)
    }
}
