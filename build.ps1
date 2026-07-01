$env:PATH += ";C:\maven\apache-maven-3.9.16\bin"
$env:JAVA_HOME = "C:\Program Files\Java\jdk-17"

Write-Host "Building BowSMPPlugin..."
mvn clean package

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "============================================"
    Write-Host "Build successful!"
    Write-Host "JAR file location: target\BowSMPPlugin-1.0.0.jar"
    Write-Host "============================================"
} else {
    Write-Host ""
    Write-Host "Build failed!"
    Write-Host "Make sure:"
    Write-Host "- Java JDK 17 is installed"
    Write-Host "- Maven is in C:\maven\apache-maven-3.9.16\"
}

Read-Host "Press Enter to exit"
