<img width="100%" height="45%" src="https://user-images.githubusercontent.com/120348555/229406073-a773e296-0aaa-4691-b45e-8bde8fd5c87b.jpg">

## Chat GPT 상담가와 함께하는 고민상담 앱, mind meand:speech_balloon:

　
## 📌 Project Explanation
    
저희 앱은 개인들이 걱정을 나눌 수 있는 안전한 공간 부족 문제를 해결하고 지원을 받을 수 있는 환경을 제공함으로써 이 문제에 대처하고자 합니다.
이 앱을 통해 사용자들은 여러 역할의 상담가와 소통할 수 있는 개방적인 환경을 제공하고자 기획하게 되었습니다.

:speech_balloon: <b>고민상담 서비스 </b>: 현재 세명의 상담가 타입을 지원하며, 원하는 상담가를 선택하여 고민상담을 받을 수 있습니다.<br>

<div align="center">
  <h1>📌</h1>
</div>
<div align="center"> 
  <!-- 자바  --><img src="https://img.shields.io/badge/JAVA-007396?style=flat-square&logo=JAVA&logoColor=white"/>
  <!--  Android--><img src="https://img.shields.io/badge/Android-3DDC84?style=flat-square&logo=Android&logoColor=white"/>
  <!--  Android Studio --><img src="https://img.shields.io/badge/Android%20Studio-3DDC84?style=flat-square&logo=Android%20Studio&logoColor=white"/>
  <br>
  <img src="https://img.shields.io/badge/Amazon AWS-232F3E?style=flat-square&logo=Amazon AWS&logoColor=white"/>
  <img src="https://img.shields.io/badge/Amazon RDS-527FFF?style=flat-square&logo=Amazon RDS&logoColor=white"/>
  <img src="https://img.shields.io/badge/Amazon S3-569A31?style=flat-square&logo=Amazon S3&logoColor=white"/>
  <img src="https://img.shields.io/badge/AWS Lambda-FF9900?style=flat-square&logo=AWS Lambda&logoColor=white"/>
   <br>
<img src="https://img.shields.io/badge/Amazon API Gateway-FF4F8B?style=flat-square&logo=Amazon API Gateway&logoColor=white"/>   <img src="https://img.shields.io/badge/Amazon CloudWatch-FF4F8B?style=flat-square&logo=Amazon CloudWatch&logoColor=white"/>
</div>

　
## 📌 사용한 기술
Chat GPT Open API

<img width="50%" height="45%" src="https://user-images.githubusercontent.com/120348555/229407049-c686f78f-8aa0-48a6-9f1a-7b2fbe7fbbed.JPG">

## 📌Code block
```java
# recyclerView 페이징 처리 코드

recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();

                if(lastPosition + 1 == totalCount){
                    offset=offset+5;
                    getConsultationData();

                }
```

　
## 🌈 Member
|왕현성|윤지수|백민우|
|:-:|:--:|:-:|
|<img src="https://user-images.githubusercontent.com/120348500/227099410-49f69b01-7b82-45a3-ab85-1d477c7ae6d1.jpg" width="100" height="100">|<img src="https://user-images.githubusercontent.com/120348555/227101223-bbfa4b86-906f-4a33-9399-da2ed5f13fbb.jpg" alt="d00hye" width="100" height="100">|<img src="https://user-images.githubusercontent.com/120348555/228713684-a3d415b9-1a34-481b-866c-7b034a2c061a.jpg" alt="DoyKim-20" width="100" height="100">|
|[hyunsungKR](https://github.com/hyunsungKR)|[Yunwltn](https://github.com/Yunwltn)|[leobaek](https://github.com/leobaek)|
