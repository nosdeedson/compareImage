## command to run

# java -jar threads-1.0-jar-with-dependencies.jar type folder/path folder/path start end

# 'start' integer to initiate the for
# 'end' integer to finish the for
# 'type' determine the type of comparison if 'byByte' compares bytes if 'byPixel' compares pixel

# if 'start' and 'end' not passed the programm will compare all the images

# java -jar threads-1.0-jar-with-dependencies.jar byByte /home/edson/Pictures/adele /home/edson/Pictures/adele 1 10


## USING ACTOR MODEL PATTERN
# 4 /home/edson/Pictures/adele24/ /home/edson/Pictures/adele/ actor 600000
# WHERE 
# 4 = quantity of threads
# /home/edson/Pictures/adele24/ = path of the folder to compare (as /home/edson/Pictures/adele/)
# actor = indicate to use the actor pattern
# 600000 = time to sleep to wait comparing the images
