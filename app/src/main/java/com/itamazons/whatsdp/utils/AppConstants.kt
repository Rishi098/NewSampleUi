package com.itamazons.whatsdp.utils

/**
 * Constants used throughout the application
 */
object AppConstants {
    
    // Chart Configuration
    object Chart {
        const val MAX_NUMBER_OF_LINES = 4
        const val NUMBER_OF_POINTS = 6
        const val DEFAULT_STROKE_WIDTH = 2
        const val DEFAULT_POINT_RADIUS = 3
        const val DEFAULT_VIEWPORT_BOTTOM = 1.75f
        const val DEFAULT_VIEWPORT_TOP = 1.9f
        const val DEFAULT_VIEWPORT_LEFT = 6f
        const val DEFAULT_VIEWPORT_RIGHT = 17f
    }
    
    // Network Configuration
    object Network {
        const val CONNECT_TIMEOUT_SECONDS = 30L
        const val READ_TIMEOUT_SECONDS = 30L
        const val WRITE_TIMEOUT_SECONDS = 30L
    }
    
    // Database Configuration
    object Database {
        const val DATABASE_NAME = "whatsdp_database"
        const val DATABASE_VERSION = 1
    }
    
    // Shared Preferences
    object Preferences {
        const val PREF_NAME = "whatsdp_preferences"
        const val KEY_THEME = "theme_mode"
        const val KEY_FIRST_LAUNCH = "first_launch"
        const val KEY_CHART_SETTINGS = "chart_settings"
    }
    
    // Animation Durations
    object Animation {
        const val SHORT_DURATION = 200L
        const val MEDIUM_DURATION = 300L
        const val LONG_DURATION = 500L
    }
    
    // File Extensions
    object FileTypes {
        const val IMAGE_JPEG = "image/jpeg"
        const val IMAGE_PNG = "image/png"
        const val IMAGE_WEBP = "image/webp"
        const val IMAGE_GIF = "image/gif"
    }
    
    // Error Messages
    object ErrorMessages {
        const val NETWORK_ERROR = "Network connection error"
        const val DATA_LOADING_ERROR = "Failed to load data"
        const val CHART_RENDERING_ERROR = "Failed to render chart"
        const val IMAGE_LOADING_ERROR = "Failed to load image"
        const val UNKNOWN_ERROR = "An unknown error occurred"
    }
    
    // Success Messages
    object SuccessMessages {
        const val DATA_LOADED = "Data loaded successfully"
        const val IMAGE_SAVED = "Image saved successfully"
        const val SETTINGS_UPDATED = "Settings updated successfully"
    }
}
