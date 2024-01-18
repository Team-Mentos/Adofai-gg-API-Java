# Adofai-gg-API-Java

Page : https://jd.krmentos.xyz/adofai-gg-api/
## 이 ADOFAI.GG API JAVA는 뭔가요?
---
ADOFAI.GG 사이트의 데이터를 가져올 수 있는 API입니다.
---

## 사용법
Java 버전 17이상을 요구합니다.
---
Gradle
```
dependencies {
  implementation 'xyz.krmentos:adofai-gg-api:1.0.1'
}
```

Maven 사용법
```
<dependency>
  <groupId>xyz.krmentos</groupId>
  <artifactId>adofai-gg-api</artifactId>
  <version>1.0.1</version>
</dependency>
```
---
## 코드 사용법의 예시
---
```
        // AdofaiGG 개체 생성
        AdofaiGG adofaiGG = new AdofaiGG(LoadOption.LOAD_EVERY_ACTIVE);
        // new AdofaiGG(), new AdofaiGG(LoadOption, (long) Time) 형식도 사용 가능


        // AdofaiGG에서 1번맵 데이터 받아오기
        MapData mapData = adofaiGG.getMapById(1);

        // AdofaiGG에서 곡 제목에 a가 들어간 맵 데이터들 받아오기
        List<MapData> maps = adofaiGG.getMapByQuery(new MapQuery().setSong("a"));

        // AdofaiGG에서 1번맵 클리어 받아오기
        List<ClearData> clears = adofaiGG.getClearByQuery(new ClearQuery().setMapId(1));

        // AdofaiGG에서 1번 유저 정보 받아오기
        UserData userData = adofaiGG.getUserById(1);
        
        // AdofaiGG에서 모든 맵 데이터 받아오기
        MapData[] allMap = adofaiGG.getMaps();
```
---

Made By Mentos
