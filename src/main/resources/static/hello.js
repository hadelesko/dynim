

/*
function onCheckboxChanged(checked){
	if(checked)
		$("#hiddenRow").fadeIn(1500 ,'linear');

	else $("#hiddenRow").fadeOut(900 ,'linear');
}


<html>
<head>
<title>Form</title>
<link rel="stylesheet" href="style.css" />
<script src="jquery.js"></script>
<script src="scripts.js"></script>
</head>
<form action="" method="POST">
<table>
<tbody>
<tr><td colspan="2"><h1 id="form_title">Form</h1></td></tr>
<tr><td>Name : </td><td><input type="text" name="name" placeholder="Enter Name" /></td></tr>
<tr><td>Email : </td><td><input type="email" name="email" placeholder="Enter Email" /></td></tr>
<tr><td>Already a Member : </td><td><input type="checkbox" onchange="onCheckboxChanged(this.checked)" name="member" /></td></tr>
<tr id="hiddenRow" style="display:none;"><td>Username : </td><td><input type="text" name="username" placeholder="Enter Username" /></td></tr>
<tr><td colspan="2"><input type="submit" name="submit" value="Submit" /></td></tr>
</tbody>
</table>
</form>
</html>*/


 function myFunction() {
    var optionlocationId = document.getElementById("locationiId");
    var inputfieldid = document.getElementById("locid");

    var optionlocationName=document.getElementById("locationName");
    var inpufieldlocationName=document.getElementById("locname");

    var optionlocationStock=document.getElementById("locationStock");
    var inpufieldlocationStock=document.getElementById("locstock");

    if (optionlocationId.selected == true){
        inputfieldid.style.display = "block";
    } else {
      inputfieldid.style.display = "none";
    }

    if (optionlocationName.selected == true){
        inputfiellocationName.style.display = "block";
    } else {
      inputfieldlocationName.style.display = "none";
    }

     if (optionlocationStock.selected == true){
        inputfieldlocationStock.style.display = "block";
    } else {
      inputfieldlocationStock.style.display = "none";
    }
}

