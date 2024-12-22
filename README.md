# Spring Boot E-Ticaret Projesi

Bu proje, Spring Boot kullanılarak geliştirilmiş bir e-ticaret uygulamasıdır. Proje, ürün, müşteri, sepet ve sipariş yönetimini kapsamaktadır. Kullanıcılar ürünleri sepete ekleyebilir, sipariş oluşturabilir ve geçmiş siparişlerini görüntüleyebilir.

## Proje Yapısı
- **Entity**: Veritabanı tablolarını temsil eden sınıflar (`Product`, `Customer`, `Cart`, `Order`, `BaseEntity`).
- **DTO**: Veri transferi için kullanılan sınıflar.
- **Controller**: HTTP isteklerini karşılayan katman.
- **Service**: İş mantığını içeren katman.
- **Repository**: Veritabanı işlemlerini gerçekleştiren katman.

## Temel Özellikler
- **Müşteri ve İlişkiler**: Bir müşterinin bir sepeti ve birden fazla siparişi olabilir.
- **Sepet Yönetimi**: Sepet fiyatı, ürün ekleme/çıkarma veya miktar değişikliklerinde otomatik güncellenir.
- **Stok Takibi**: Stokta olmayan ürünler için sipariş verilemez.

## Uygulama Servisleri
- **Müşteri Servisleri**: Müşteri ekleme.
- **Ürün Servisleri**: Ürün ekleme, güncelleme, silme ve görüntüleme.
- **Sepet Servisleri**: Sepet görüntüleme, güncelleme, boşaltma, ürün ekleme/çıkarma.
- **Sipariş Servisleri**: Sipariş oluşturma, kod ile sipariş sorgulama, müşteri siparişlerini listeleme.

## Test Durumları (Test Cases)
Proje kapsamında aşağıdaki test senaryoları hazırlanmıştır:
- **Ürün Servisleri Testleri**:
    - `CreateProduct`: Ürün başarıyla oluşturuluyor mu?
    - `UpdateProduct`: Ürün bilgileri doğru şekilde güncelleniyor mu?
    - `DeleteProduct`: Ürün silme işlemi düzgün çalışıyor mu?
    - `GetProduct`: Ürün bilgisi doğru şekilde alınıyor mu?
- **Sepet Servisleri Testleri**:
    - `AddProductToCart`: Sepete ürün ekleme işlemi doğru çalışıyor mu?
    - `RemoveProductFromCart`: Sepetten ürün çıkarma işlemi hatasız çalışıyor mu?
    - `UpdateCart`: Sepetteki ürün miktarı doğru güncelleniyor mu?
    - `EmptyCart`: Sepet başarıyla boşaltılıyor mu?
- **Sipariş Servisleri Testleri**:
    - `PlaceOrder`: Sipariş oluşturma işlemi stok kontrolü ile doğru çalışıyor mu?
    - `GetOrderForCode`: Sipariş detayları doğru şekilde getiriliyor mu?
    - `GetAllOrdersForCustomer`: Müşteriye ait tüm siparişler listeleniyor mu?
- **Stok Yönetimi Testleri**:
    - Stok bitmiş bir ürüne sipariş verilmesi engelleniyor mu?

Testler, JUnit  kullanılarak geliştirilmiştir.
