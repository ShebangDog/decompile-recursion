FROM sbtscala/scala-sbt:eclipse-temurin-focal-17.0.10_7_1.9.9_3.4.0

WORKDIR /decompile-recursion

COPY . .

CMD ["sbt", "decompile"]
