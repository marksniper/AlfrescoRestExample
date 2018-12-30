var allElement = "";
$(document).ready(function ()
{
    login().then(function (idNodo) {

        $.ajax({
            url: "http://localhost:8010/node/treedirectory",
            type: "POST",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            crossDomain: true,
            success: function (data) {
                var tree = JSON.parse(JSON.stringify(data));
                allElement = "<ul id='listaFileFolder'>";
                allElement = allElement + "<li class=\"folder\" id=\"" + idNodo['entry']['id'] + "\">" + idNodo['entry']['name'] + "</li>";
                allElement = allElement + "<ul>";
                var child = tree['root']['children'];
                stampaAlbero(child);
                allElement = allElement + "</ul>";
                allElement = allElement + "</ul>";
                $("#fileFolder").append(allElement);
            },
            error: function (err)
            {
                console.log("data " + (JSON.stringify(err)));
            }
        });

    }).catch(function (err) {
        console.log(err);
    });

});


function stampaAlbero(child) {

    if (!(child.length == 0)) {
        var i = 0;
        while (i < child.length) {
            var leaf = child[i];
            var classeDiv = "";
            if (leaf['data']['entry']['isFolder']) {
                classeDiv = "folder";
            }
            if (leaf['data']['entry']['isFile']) {
                classeDiv = "file";
            }
            allElement = allElement + "<li class='" + classeDiv + "' id='" + leaf['data']['entry']['id'] + "'>" + leaf['data']['entry']['name']
                + "</li>";
            if (leaf['data']['entry']['isFolder']) {
            }

            leaf = leaf['children'];
            if (!(leaf.length === 0)) {
                allElement = allElement + "<ul id='listaFileFolder'>";
                stampaAlbero(leaf);
                allElement = allElement + "</ul>";
            }
            i++;
        }

    }

}

function login()
{
    return new Promise(function (resolve, reject) {
        $.ajax({
            url: "http://localhost:8010/node/info/-my-",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            async: true,
            crossDomain: true,
            success: function (data) {
                resolve(data);
            },
            error: function (err) {
                reject(err);
            }
        });
    })
}
