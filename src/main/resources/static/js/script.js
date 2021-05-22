
$(document).ready(function(){

    const URL = "http://localhost:8080";
    //const URL = "http://103.140.181.124:8080/sandstorm";

    getResources();
    getDevices();

    function status(flag){

        return (flag == true) ? "Yes" : "No";
    }

    function gender(gender){

        return (gender == 'M') ? "Male" : "Female";
    }

    function getResources(){

        $.get(URL + "/api/resources", function( data ) {

            const response = $.parseJSON(JSON.stringify(data));

            let html = "";

            for(let i=0; i<response.length; i++){

                html += "<tr>";
                html += "<td>" + (i+1) + "</td>";
                html += "<td>" + response[i].deviceName + "</td>";
                html += "<td>" + response[i].applicationName + "</td>";
                html += "<td>" + response[i].applicationPackage + "</td>";
                html += "<td>" + response[i].apkVersion + "</td>";
                html += "<td>" + status(response[i].camera) + "</td>";
                html += "<td>" + status(response[i].microphone) + "</td>";
                html += "<td>" + status(response[i].gps ) + "</td>";
                html += "<td>" + status(response[i].contact) + "</td>";
                html += "<td>" + status(response[i].sms) + "</td>";
                html += "<td>" + status(response[i].memory) + "</td>";
                html += "<td>" + response[i].time + "</td>";
                html += "</tr>";
            }

            $('#resources').append(html);

        }, "json" );
    }

    function getDevices(){

        $.get(URL + "/api/devices", function( data ) {

            const response = $.parseJSON(JSON.stringify(data));

            let html = "";

            for(let i=0; i<response.length; i++){

                html += "<tr>";
                html += "<td>" + (i+1) + "</td>";
                html += "<td>" + response[i].name + "</td>";
                html += "<td>" + response[i].mac + "</td>";
                html += "<td>" + response[i].androidVersion + "</td>";
                html += "<td>" + response[i].userAgeGroup + "</td>";
                html += "<td>" + response[i].userArea + "</td>";
                html += "<td>" + response[i].userEducation + "</td>";
                html += "<td>" + gender(response[i].userGender) + "</td>";
                html += "</tr>";
            }

            $('#devices').append(html);

        }, "json" );
    }

});