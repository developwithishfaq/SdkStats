package data.network

import data.utils.MyPref
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

class SupaProvider(
    private val prefs: MyPref
) {

    fun getSupabase(): SupabaseClient {
        val supabaseClient: SupabaseClient = createSupabaseClient(prefs.databaseUrl, prefs.dbSecretKey) {
            install(Postgrest)
        }
        return supabaseClient
    }
}