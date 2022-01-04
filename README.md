# Tidings
- [Newsapi.org](https://newsapi.org/) Api'sindeki haberler için arama yapabilen ve bunları bir Paging 3 kütüphanesi kullanılarak RecyclerView'da görüntülenebilen bir uygulamadır.
- Yani bu uygulama Newsapi.org sitesinde var olan haberleri api üzerinden çekip uygulama içerisinde gösterildi.
- Uygulama içerisinde NavigationBottomBar sayesinde sayfalar arasında geçişler sağlandı.
- Filtreleme yaparak haberleri haber başlıklarıyla veya haber kategoriyle filtreleme yaparak haberler listeleniyor.
- Listeleme içerisinde istenilen haberin üzerine tıklanarak o haberin sitesine doğrudan uygulamadan çıkmadan erişilip haberin detaylarını görebiliyoruz.
- İlgili haberlerin detay sayfası içerisindeki floating buton sayesinde ilgili haberi kayıt edip database'e ekleniyor. 
- Kayıt edilen haberler içerisinde ise filtreleme işlemi yapılarak haberler aranabiliyor ve listenebiliyor.

- ## Bu yapılmış uygulamada android geliştirme için ileri seviye teknolojiler kullanılmıştır.
- ### Kullanılan Teknolojiler:
- MVVM Architecture
- Dagger Hilt
- Retrofit
- Flow
- Lifecycle
- Fragments
- Paging 3
- Glide
- View Binding
- Navigation Components
- Coroutines 
- ## Bu Uygulamada Neler Öğreneceksiniz?
- Newsapi üzerinden verileri retroit kütüphanesindeki yapıları kullanarak daha sağlıklı nasıl çekildiğini
- Pagination İşlemi yani Sayfalandırma başka bir deyişle Sonsuz Sayfalandırma yapısını RecyclerView'le nasıl kullanıldığını
- Pagination işlemleri içerisindeki hearder ve footer kavramlarını kullanarak Recyclerview yapısını esnek bir hale getirip hata mesajlarını ve network içerisindeki kontrolleri kolay bir şekilde yapmamıza olanak sağlıyor.
- View Binding implementasyonu ve kullanımı
- Navigation Componentlerinin nasıl kullanılması gerektiğini
- Dagger Hilt sayesinde modeller ve View arasındaki bağlantıları, Application içerisindeki bağlantıları, Retrofitle olan bağlantıların nasıl oluşturulduğunu 
- Recyclerview içerisinde DiffUtil kullanımını öğreneceksiniz.
- Kotlin Flow yapısının database içerisinde kullanıp ViewModel içerisinde ise nasıl kullanılması gerektiği ve View e nasıl aktarılması gerektiğini 
## ScreenShots

![Alt Text](https://github.com/halilkrkn/Tidings/blob/master/screenshots/tidings.gif)
