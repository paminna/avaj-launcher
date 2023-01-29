find * -name "*.java" > sources.txt
javac -d target @sources.txt
jar -cmf src/manifest.txt avaj-launcher.jar -C target .
java -jar avaj-launcher.jar scenario.txt
