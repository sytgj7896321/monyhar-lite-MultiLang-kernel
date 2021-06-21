using System;
using System.Net;
using System.IO;

namespace C_
{
    class Program
    {
        static void Main(string[] args)
        {
            //C# WebRequest方法默认使用系统配置代理

            string url = Console.ReadLine();
            string old_url = url;
            if (!url.StartsWith("www.")) url = "www." + url;
            Console.WriteLine("Auto inserted 'www.");
            if (!url.StartsWith("http://")) url = "https://" + url;
            Console.WriteLine("Auto inserted 'http://'.");

            Monyhar internet = new Monyhar(url);
            internet.surf_internet();
            Console.WriteLine(internet.html);

            Console.WriteLine("Help-About?[Y/n]");
            if (Console.ReadLine() == "Y")
                Console.WriteLine(internet.about());
            Console.WriteLine("Do you want to download the page?[Y/n]");
            if (Console.ReadLine() == "Y")
                internet.save_html(old_url);
        }
    }
    class Monyhar
    {
        public string url { get; private set; }
        public string html { get; private set; }
        public Monyhar(string url)
        {
            this.url = url;
        }
        public void surf_internet()
        {
            WebRequest wr = WebRequest.Create(url);
            WebResponse wres = wr.GetResponse();
            Stream stream = wres.GetResponseStream();
            StreamReader sr = new StreamReader(stream);
            this.html = sr.ReadToEnd();
            sr.Close();
            stream.Close();
            wres.Close();
        }
        public string about()
        {
            return 
@"Monyhar Browser,made by tucaoba233.
©CopyRight 2021-2021 tucaoba233, All Rights Reserved.
This project use GPL-3.0 License";
        }
        public void save_html(string old_url)
        {
            using (StreamWriter sw = new StreamWriter($"{Environment.CurrentDirectory}\\{old_url.Replace('/', '_')}.html"))
            {
                sw.Write(html);
            }
        }
    }
}
