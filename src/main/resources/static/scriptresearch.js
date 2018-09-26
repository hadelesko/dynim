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