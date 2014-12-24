#!/bin/bash
declare -x dpval="Offline"
getIP(){
declare -x DIP='X'
until [ $DIP != "X" ]
    do
		wget -q --tries=10 --timeout=20 --spider http://google.com
		if [[ $? -eq 0 ]]; then
			   DIP=$(echo `wget http://ipecho.net/plain -O - -q`)
				if [ "$DIP" == "" ];then
				   DIP='X'
				fi
			   echo $DIP
		else
			   DIP="Offline"
			   echo $DIP
		fi
    done	
	
 return 0
 } 
dpval=$(getIP)

if [[ $dpval = "Offline" ]]; then
    dpval=$(getIP)
fi
echo  "$dpval"
