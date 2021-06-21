import urllib.request


class HtmlDownloader:
    def get_html(self):
        html = urllib.request.urlopen(self).read()
        return html

    def save_html(file_name, file_content):
        #    注意windows文件命名的禁用符，比如 /
        with open(file_name.replace('/', '_') + ".html", "wb") as f:
            #   写文件用bytes而不是str，所以要转码
            f.write(file_content)
            f.close()



