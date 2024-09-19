package data.utils

import java.util.prefs.Preferences

class MyPref {
    private val prefs: Preferences = Preferences.userRoot().node("my_app_prefs")
    fun savePreference(key: String, value: String) {
        prefs.put(key, value)
    }

    fun getPreference(key: String, defaultValue: String): String {
        return prefs.get(key, defaultValue)
    }

    var databaseUrl: String
        get() = getPreference("databaseUrl", "https://dnbsjqscqvhpfgpdxzma.supabase.co")
        set(value) = savePreference("databaseUrl", value)

    var dbSecretKey: String
        get() = getPreference("dbSecretKey", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRuYnNqcXNjcXZocGZncGR4em1hIiwicm9sZSI6InNlcnZpY2Vfcm9sZSIsImlhdCI6MTcyNjU2NTA5OCwiZXhwIjoyMDQyMTQxMDk4fQ.2jw2TonHK_qbNaycn2kzSrLtOHYVrzvA3MRR9Co1o9o")
        set(value) = savePreference("dbSecretKey", value)

    fun setDatabaseValue(key: String, url: String) {
        databaseUrl = url
        dbSecretKey = key
    }

}