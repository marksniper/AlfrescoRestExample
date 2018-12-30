var allElement = "";
$(document).ready(function () {
    login().then(function (idNodo)
    {

        $.ajax({
            url: "http://localhost:8010/node/treedirectory",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            crossDomain: true,
            success: function (data) {
                var tree = JSON.parse(JSON.stringify(data));
                $('#idCartellaPadre').attr('value',idNodo['entry']['id']);
                allElement = "<div class=\"list\"> <div class=\"item\">" +
                    "                            <i id=\"" + idNodo['entry']['id'] + "\" class=\"huge green folder icon\" data-value='"+idNodo['entry']['name'] + "'></i>" +
                    "                            <div class=\"content\">" +
                    "                                <div id=\"" + idNodo['entry']['id'] + "\" class=\"header\">" + idNodo['entry']['name'] + "</div></div></div></div><div class=\"list\"> ";
                var child = tree['root']['children'];
                stampaAlbero(child);
                allElement = allElement + " </div></div></div>";
                $("#fileFolder").append(allElement);
            },
            error: function (err) {
                console.log("data " + (JSON.stringify(err)))
            }
        });

    }).catch(function (err) {
        console.log(err);
    });

});


function stampaAlbero(child) {

    if (!(0 === child.length)) {
        var i = 0;
        while (i < child.length) {
            var leaf = child[i];
            var classeDiv = "";
            if (leaf['data']['entry']['isFolder']) {
                //console.log("E' una cartella");
                classeDiv = "big blue folder icon";
            }
            if (leaf['data']['entry']['isFile']) {
                // console.log("E' un file");
                classeDiv = " large green file icon";
            }
            allElement = allElement + "<div class=\"item\">" +
                "                                        <i data-value='"+ leaf['data']['entry']['name']+ "' id=\"" + leaf['data']['entry']['id'] + "\"  class=\"" + classeDiv + "\"></i>" +
                "                                        <div class=\"content\">" +
                "                                            <div id=\"" + leaf['data']['entry']['id'] + "\"  class=\"header\">" + leaf['data']['entry']['name'] +
                "</div></div></div>";
            if (leaf['data']['entry']['isFolder']) {
            }

            leaf = leaf['children'];
            if (!(leaf.length === 0)) {
                allElement = allElement + "<div class=\"list\">";
                stampaAlbero(leaf);
                allElement = allElement + "</div> ";
            }
            i++;
        }

    }

}

function login() {
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: "http://localhost:8010/node/info/-my-",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            crossDomain: true,
            success: function (data) {
                resolve(data); // Resolve promise and go to then()
            },
            error: function (err) {
                reject(err); // Reject the promise and go to catch()
            }
        });
    })
}
