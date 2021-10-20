package com.itamazons.whatsdp.SharePreferene

import android.content.Context
import android.content.SharedPreferences

class SharePref {


    val SHARED_PREF_NAME = "whatsdp"
    fun InitizeSharePref(context: Context) {
        sharedPreferences =
            context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    companion object {
        private var sharedPreferences: SharedPreferences? = null
        private var sharePref: SharePref? = null

        //login detail
        const val IS_LIKED = "is_liked"
        const val SELECTEDTHEME = "selectedtheme"
        const val IS_NEW_USER = "is_new_user"
        const val REFRESHTIME = "refreshtime"
        const val UPDATEVISITORLISTTIME = "updatevisitorlist"
        const val UPDATEVISITEDLISTTIME = "updatevisitedlist"
        const val REMOVE_ADS = "rmb_bgn_id"
        const val PRO = "por_hgn_id"
        const val FREE = "get_don_id"
        const val FIRSTTIME = "firsttime"
        const val FORCEUPDATEAPI = "forceupdateapi"
        const val MESSAGE = "message"
        const val UPDATE_URL = "update_url"
        const val FORCE_UPDATE = "force_update"
        const val UPDATE_REQUIRED = "update_required"
        const val APP_VERSION = "app_version"
        const val PRIVACY_POLICY = "privacypolicy"
        const val IS_RATED = "is_rated"
        const val INTERSTITIALADSCLICK = "Interstitialadsclick"
        const val SPLASHADS = "SplashAds"
        const val TIME_FOR_RATE = "time_for_rate"
        const val BASE_URL = "BASE_URL"
        const val POST_LOAD_MORE = "POST_LOAD_MORE"
        const val VIDEO_LOAD_MORE = "VIDEO_LOAD_MORE"
        const val PUBLIC_POST_LOAD_MORE = "PUBLIC_POST_LOAD_MORE"

        const val PROFILE_SEARCH_API = "PROFILE_SEARCH_API"
        const val PROFILE_BASE_URL = "PROFILE_BASE_URL"
        const val INSOVERLAY_LIKE = "INSOVERLAY_LIKE"
        const val INSOVERLAY_PROFILEPIC = "INSOVERLAY_PROFILEPIC"

        const val INSOVERLAY_FEEDBACK = "INSOVERLAY_FEEDBACK"
        const val INSOVERLAY_SHARE = "INSOVERLAY_SHARE"
        const val INSOVERLAY_POST = "INSOVERLAY_POST"
        const val INSOVERLAY_SETTING = "INSOVERLAY_SETTING"

        val instance: SharePref?
            get() {
                if (sharePref == null) {
                    sharePref = SharePref()
                }
                return sharePref
            }

        fun ClearSharePref() {
            sharedPreferences!!.edit().clear().commit()
        }

        fun savesharePrefBooleanValue(key: String?, value: Boolean?) {
            sharedPreferences!!.edit().putBoolean(key, value!!).commit()
        }

        fun getSharePrefLongValue(key: String?): Long {
            return if (sharedPreferences == null) {
                0
            } else sharedPreferences!!.getLong(key, 0)
        }

        fun getSharePrefLongValue(key: String?, defaultValue: Long): Long {
            return if (sharedPreferences == null) {
                0
            } else sharedPreferences!!.getLong(key, defaultValue)
        }

        fun savesharePrefLongValue(key: String?, value: Long) {
            sharedPreferences!!.edit().putLong(key, value).commit()
        }

        fun getSharePrefBooleanValue(key: String?): Boolean {
            return if (sharedPreferences == null) {
                false
            } else sharedPreferences!!.getBoolean(key, false)
        }

        fun getSharePrefBooleanValue(key: String?, value: Boolean): Boolean {
            return if (sharedPreferences == null) {
                false
            } else sharedPreferences!!.getBoolean(key, value)
        }

        fun getSharePrefStringValue(
            key: String?,
            defaultValue: String?
        ): String? {
            return if (sharedPreferences == null) {
                ""
            } else sharedPreferences!!.getString(key, defaultValue)
        }

        fun getSharePrefStringValue(key: String?): String? {
            return if (sharedPreferences == null) {
                ""
            } else sharedPreferences!!.getString(key, "")
        }


        fun savesharePrefStringValue(key: String?, value: String?) {
            sharedPreferences!!.edit().putString(key, value).commit()

        }

        fun getSharePrefIntValue(key: String?): Int {
            return if (sharedPreferences == null) {
                0
            } else sharedPreferences!!.getInt(key, 0)
        }

        fun getSharePrefIntValue(key: String?, defaultvalue: Int): Int {
            return if (sharedPreferences == null) {
                defaultvalue
            } else sharedPreferences!!.getInt(key, defaultvalue)
        }

        fun savesharePrefIntValue(key: String?, value: Int) {
            sharedPreferences!!.edit().putInt(key, value).commit()
        }


        fun getIntValue(key: String?): Int {
            return if (sharedPreferences == null) {
                0
            } else sharedPreferences!!.getInt(key, 0)
        }

        fun getIntValue(key: String?, defaultvalue: Int): Int {
            return if (sharedPreferences == null) {
                0
            } else sharedPreferences!!.getInt(key, defaultvalue)
        }

        fun saveIntValue(key: String?, value: Int) {
            sharedPreferences!!.edit().putInt(key, value).commit()
        }

        fun getLongValue(key: String?): Long {
            return if (sharedPreferences == null) {
                0
            } else sharedPreferences!!.getLong(key, 0)
        }

        fun getLongValue(key: String?, defaultvalue: Long): Long {
            return if (sharedPreferences == null) {
                0
            } else sharedPreferences!!.getLong(key, defaultvalue)
        }

        fun saveLongValue(key: String?, value: Long) {
            sharedPreferences!!.edit().putLong(key, value).commit()
        }
    }
}
