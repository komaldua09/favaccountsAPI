FROM fabric8/java-centos-openjdk11-jre
EXPOSE 8080
ADD target/FavouriteAccounts-0.0.1-SNAPSHOT.jar FavouriteAccountsAPI.jar
ENTRYPOINT ["java", "-jar", "FavouriteAccountsAPI.jar"]