$(document).on('click', '#listaFileFolder li', function ()
{
    var tree = TreeView;
    var array =["Hello", "Marco"];
    tree._getTree("aa","bb",array,
        function (data)
        {
            console.log("UseserviceIndex: "+JSON.stringify(data));

        },
        function(err)
        {
            console.log("errore: "+JSON.stringify(err));
        }
    );
    var prova = Prova;
    prova.setLastName("Hello")
    tree._getInfoNode(this.id,
        function (data)
        {
            console.log("UseserviceIndex: "+JSON.stringify(data));
        },
        function (err)
        {
            console.log("errore: "+JSON.stringify(err));
        });
    prova.ciao ="Musica schifosa";
    console.log(prova.ciao);

});