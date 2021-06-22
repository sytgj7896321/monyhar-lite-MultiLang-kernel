program Monyhar;

uses
    System.SysUtils, IdHTTP, Classes;

Procedure surf_internet(url: string);
var
    html: string;
    ResponseString: string;
    IdHttp: TIdHTTP;
    ResponseStream: TStringStream;
begin
    IdHttp := TIdHTTP.Create(nil); // Create IdHTTP widget
    ResponseStream := TStringStream.Create(''); // Save response
    IdHttp.Get(url,ResponseStream);
    html := ResponseStream.DataString;
    html := UTF8Decode(html); //UTF8 Decode for Chinese content
    writeln(html);
    IdHttp.Free;
    ResponseStream.Free;
end;

var
    url: string;
begin
    readln(url);
    surf_internet(url);
    readln;
end.
