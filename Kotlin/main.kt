package monyhar.kernel.kotlinPart;
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

fun execute(COMPLETE_URL: String?): String? {
    val text:String? = URL(COMPLETE_URL).readText()
    return text
}
fun ping(COMPLETE_URL: String?):String{
    var ping_process = Runtime.getRuntime().exec(COMPLETE_URL)
    ping_process.waitFor()
    var bufrIn = BufferedReader(InputStreamReader(ping_process.inputStream, "UTF-8"))
    var bufrError = BufferedReader(InputStreamReader(ping_process.errorStream, "UTF-8"))
    var ping_result = bufrIn.readText()+bufrError.readText()
    return ping_result
}
fun main() {
    print("Do you want to set proxy server?[Y/n]")
    var if_proxy = readLine() ?: "n"

    if (if_proxy == "Y") {
        println("Type http proxy address here.")
        var http_proxy = readLine()
        println("Type https proxy address here.")
        var https_proxy = readLine() //proxies: dict[str, Any] = {"http": http_proxy, "https": https_proxy}
        println("Test the proxy server?[Y/n]")
        var if_test = readLine() ?: "n"
        if (if_test == "Y") {
            var ping_http_proxy = "ping$http_proxy"
            var ping_https_proxy = "ping$https_proxy"
            var ping_result = ping(ping_http_proxy)
            if (ping_result in "Lost = 0") {
                println("Connected to the http proxy server.")
                ping_result = ping(ping_https_proxy)
            }
            if ("Lost = 0" in ping_result)
                println("Connected to the https proxy server.")
        }
    } else
        println("No proxy server was set.")
    print("url:")
    var url: String? = readLine()
    if(!url?.contains("www.")!!)
    {
        url = "www.$url"
        println("Auto inserted 'www.")
    }
    if( "http://" !in url) {
        url = "http://$url"
        println("Auto inserted 'http://'.")
    }
    Monyhar.surf_internet(url)
    Monyhar.about()
}

object Monyhar {
    fun surf_internet(url:String?) {

        var html = execute(url)
        print(html)
    }
    fun about() {
        print("Monyhar Browser,made by tucaoba233.")
        print("©CopyRight 2021-2021 tucaoba233, All Rights Reserved.")
    }
}
