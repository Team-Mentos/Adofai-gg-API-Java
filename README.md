# Adofai-gg-API-Java

* 이 API는 자바에서 ADOFAI.GG의 사이트에서 데이터를 가져올 수 있는 API입니다.


## 유의사항
* Java 버전 17이상을 요구합니다.

## 사용법의 경우

### Gradle
```
dependencies {
  implementation 'xyz.krmentos:adofai-gg-api:1.0.1'
}
```

### Maven
```
<dependency>
  <groupId>xyz.krmentos</groupId>
  <artifactId>adofai-gg-api</artifactId>
  <version>1.0.1</version>
</dependency>
```

## 코드 사용법의 예시
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

## License
ADOFAI.GG JAVA API의 경우 "BSD 3-Clause "New" or "Revised" License"를 사용합니다.
```
BSD 3-Clause License

Copyright (c) 2024, Team Mentos

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.

2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

3. Neither the name of the copyright holder nor the names of its
   contributors may be used to endorse or promote products derived from
   this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
```

#### Made By Mentos
