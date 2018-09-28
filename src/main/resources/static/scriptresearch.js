function myFunction() {
var optionlocationId = document.getElementById("locationiId");
var inputfieldid = document.getElementById("locid");

var optionlocationName=document.getElementById("locationName");
var inpufieldlocationName=document.getElementById("locname");

var optionlocationStock=document.getElementById("locationStock");
var inpufieldlocationStock=document.getElementById("locstock");

if (optionlocationId.checked == true){
inputfieldid.style.display = "block";
} else {
inputfieldid.style.display = "none";
}

if (optionlocationName.checked == true){
inputfiellocationName.style.display = "block";
} else {
inputfieldlocationName.style.display = "none";
}

if (optionlocationStock.checked == true){
inputfieldlocationStock.style.display = "block";
} else {
inputfieldlocationStock.style.display = "none";
}
}

function myyfunction(liste){
        liste=[]
        var numero=liste.selectedIndex;
        var valeur=liste.options[numero].value;
        alert("you choose : " + valeur +" for the search");

        var inputfieldid = document.getElementById("locid");
        var inpufieldlocationName=document.getElementById("locname");
        var inpufieldlocationStock=document.getElementById("locstock");


        if(liste.selectedIndex==0){
            inputfieldid.style.display="block";
        }else{
              inputfieldid.style.display = "none";}

        if(liste.selectedIndex==1){
            inpufieldlocationName.style.display="block";
        }else{
              inpufieldlocationName.style.display = "none";}

        if(liste.selectedIndex==2){
           inpufieldlocationStock.style.display="block";
        }else{
           inpufieldlocationStock.style.display = "none";}

    }