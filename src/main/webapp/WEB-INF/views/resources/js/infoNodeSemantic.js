$(document).on('click','#fileFolder i ',function()
{
    var cartellaOFile = document.getElementById(this.id);
    cartellaOFile.getAttribute("class");
    var str1 = cartellaOFile.getAttribute("class");
    var str2 = "folder";
    if(str1.indexOf(str2) == -1)
    {
        return;
    }

    $('#informazioniNodiChild').text("");
    $('#idCartellaPadre').attr('value',this.id);
    var idHiddenPereID = document.getElementById("idCartellaPadre");
    $('#nomeDellaCartellaInAnalisi').text("");
    $('#nomeDellaCartellaInAnalisi').append("Cartella in analisi: "+cartellaOFile.getAttribute("data-value"));
    getChildren(this.id).then(function (resolve)
    {

    }).catch(function (err) {

    });


});

$(document).ready(function () {
    getChildren("-my-").then(function (resolve)
    {
        $('#nomeDellaCartellaInAnalisi').append("Cartella principale");

    }).catch(function (err) {

    });

});

function getChildren(idNodoPadre)
{
    return new Promise(function (resolve, reject)
    {
        $('#informazioniNodiChild').text();
        $.ajax({
            url: "http://localhost:8010/node/child/" + idNodoPadre,
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            crossDomain: true,
            success: function (data)
            {
                var datiChild = data ['list']['entries'];
                if(!(datiChild.length > 0))
                {
                    return ;
                }
                var visualizzazioneStrutturaChild = "";
                visualizzazioneStrutturaChild = "<div class=\"ui "+datiChild.length+" column grid\">";
                var i =0;
                $('#idCartellaPadre').attr('value',datiChild[0]['entry']['parentId']);
                for(i =0; i< datiChild.length; i++)
                {
                    var datacreazione = new Date("" + datiChild[i]['entry']['createdAt']);

                    var dataModifica = new Date("" + datiChild[i]['entry']['modifiedAt']);

                    var tipoDiNodo ="";

                    var grandezzaDelFile ="";

                    if (datiChild[i]['entry']['isFolder'])
                    {
                        tipoDiNodo = "big blue folder icon";
                    }
                    if (datiChild[i]['entry']['isFile'])
                    {
                        tipoDiNodo= "large ";
                        grandezzaDelFile ="File size: " + bytesToSize(datiChild[i]['entry']['content']['sizeInBytes']);
                        switch(datiChild[i]['entry']['content']['mimeTypeName'])
                        {
                            case "Microsoft PowerPoint": {
                                tipoDiNodo = tipoDiNodo + "orange file powerpoint";
                                break;
                            }
                            case "JPEG Image": {
                                tipoDiNodo = "teal file image";
                                break;
                            }
                            case "Plain Text": {
                                tipoDiNodo = tipoDiNodo + "grey file image";
                                break;
                            }
                            case "Adobe PDF Document": {
                                tipoDiNodo = tipoDiNodo + "red file pdf";
                                break;
                            }
                            case "Microsoft Word": {
                                tipoDiNodo = tipoDiNodo + "blue file word";
                                break;
                            }
                            case "ZIP": {
                                tipoDiNodo = tipoDiNodo + "green file archive";
                                break;
                            }
                            case "": {
                                tipoDiNodo = tipoDiNodo + "";
                                break;
                            }
                            case "": {
                                tipoDiNodo = tipoDiNodo + "";
                                break;
                            }
                            case "": {
                                tipoDiNodo = tipoDiNodo + "";
                                break;
                            }
                            default: {
                                tipoDiNodo = "green file ";
                                break;
                            }
                        }
                        tipoDiNodo = tipoDiNodo + " icon";
                    }
                    var aggiustoMinuto ="";
                    var aggiustoOra ="";
                    if(datacreazione.getMinutes()<10)
                    {
                        aggiustoMinuto="0";
                    }
                    if(datacreazione.getHours()<10)
                    {
                        aggiustoOra="0";
                    }



                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+ " <div class=\"row\">";

                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+ " <div class=\"row\">";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"<div class=\"column\">";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+ "<i id=\""+datiChild[i]['entry']['id']+"\" class=\""+tipoDiNodo+"\"></i>";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";

                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"<div class=\"column\">";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"Nome: "+datiChild[i]['entry']['name'];
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";


                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"<div class=\"column\">";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"Creato il "+ datacreazione.getDate() + "/" + (datacreazione.getMonth() + 1) +
                        "/" + datacreazione.getFullYear() + " "+aggiustoOra + datacreazione.getHours() + ":" + aggiustoMinuto+ datacreazione.getMinutes();
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";

                    if(dataModifica.getMinutes()<10)
                    {
                        aggiustoMinuto="0";
                    }
                    else
                    {
                        aggiustoMinuto="";
                    }
                    if(dataModifica.getHours()<10)
                    {
                        aggiustoOra="0";
                    }
                    else
                    {
                        aggiustoOra="";
                    }
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+ " <div class=\"row\">";

                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"<div class=\"column\">";

                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"<div class=\"column\">";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"Modificato il "+ dataModifica.getDate() + "/" + (dataModifica.getMonth() + 1) +
                        "/" + dataModifica.getFullYear() + " "+ aggiustoOra + dataModifica.getHours() + ":" +aggiustoMinuto+ dataModifica.getMinutes();
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";

                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"<div class=\"column\">";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+ grandezzaDelFile;
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";
                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";

                    visualizzazioneStrutturaChild = visualizzazioneStrutturaChild+"</div>";
                }
                visualizzazioneStrutturaChild = visualizzazioneStrutturaChild +"</div>";

                $('#informazioniNodiChild').append(visualizzazioneStrutturaChild);

                resolve(data);
            },
            error: function (err)
            {
                reject(err);
            }
        });


    });
}



function bytesToSize(bytes) {
    var sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB'];
    if (bytes == 0) return '0 Byte';
    var i = parseInt(Math.floor(Math.log(bytes) / Math.log(1024)));
    return Math.round(bytes / Math.pow(1024, i), 2) + ' ' + sizes[i];
}

