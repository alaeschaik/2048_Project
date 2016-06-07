<?php

error_reporting(-1);

//check if complete
if(!isset($_GET["size"]))
	die("Please provide size.");
$x="x";
$size = $_GET["size"];
$filename = "ScoreBoard$size.csv";
if(isset($_GET["score"], $_GET["name"]))
{
	//assign variables
	$score = $_GET["score"];
	$name = $_GET["name"];
	$time = date('Y-m-d H:i:s');
	$ip = $_SERVER['REMOTE_ADDR'];
	$data = "$name;$score;$size;$time;$ip\n";

	//append or create file
	if(file_exists($filename))
		file_put_contents($filename, $data, FILE_APPEND);
	else
		file_put_contents($filename, $data);
}
else
{
	if(!file_exists($filename))
		die("file has not yet been created");
}

//deserialize file
$table = array();
$file = explode("\n",file_get_contents($filename));
for($i = 0; $i < count($file)-1; $i++) //-1 because linebreak at eof
{
	$values = explode(";",$file[$i]);
	$table[$i] = array();
	for($j = 0; $j <5; $j++)
	{
		$table[$i][$j] = $values[$j];
	}
}

//sorting
usort($table, function($a, $b){
    return $a[1] < $b[1];
});
$table = array_slice($table,0, 50);

//serialize file
$text = "";
for($i = 0; $i < count($table); $i++)
{
	for($j = 0; $j < 5; $j++)
	{
		$text .= $table[$i][$j] . (($j==4)?"":";");
	}
	$text .= "\n";
}


echo $text;

?>