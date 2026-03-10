# Refleksi 1

## Prinsip Clean Code pada Fitur Edit dan Delete Product

Prinsip Clean Code yang saya terapkan dalam mengerjakan fitur Edit dan Delete Product, saya melakukan beberapa prinsip Clean Code agar codenya mudah dibaca dan digunakan kembali oleh orang lain:

### 1. Nama dari sebuah variable harus jelas dan punya makna (Variable)

Saya menggunakan nama variabel dan method yang jelas fungsinya, seperti create, edit, delete, findById, dan productRepository.

Dengan penamaan ini, kodingan jadi lebih mudah dipahami tanpa butuh banyak komentar (self-explanatory). Contohnya, saya menggunakan productid atau productName daripada sekadar variabel satu huruf seperti pName, p, name, pid dan sebagainya.

### 2. Function (Fokus pada satu hal)

Setiap method yang saya buat fokus pada satu tugas saja.  
Method delete hanya bertugas menghapus, dan method edit hanya bertugas memperbarui data.

Logika untuk pindah halaman (redirection) tetap saya taruh di Controller, bukan di Service.

### 3. Separation of Concerns  
(Sebenarnya di module 1nya emng udah dipisah)

Module 1 memisahkan kodingan sesuai arsitektur MVC (Model-View-Controller).

- Controller hanya bertugas menangani request dari user  
- Service untuk logika bisnis  
- Repository untuk manipulasi data  

Pemisahan ini membuat kodingan tidak menumpuk di satu file sehingga lebih mudah untuk di-maintain atau diperbaiki jika ada salah satu bagian yang bermasalah.

Oh iya, tambahan disini saya menambahkan UUID.  
Hal ini sangat penting untuk mencegah serangan ID Enumeration, di mana orang asing bisa menebak-nebak ID barang lain dengan mudah hanya dengan mengganti angka di URL.

Dengan UUID yang berupa kode acak panjang, ID hampir mustahil untuk ditebak.  
(Untuk fitur edit dan delete)

Buat masalahnnya ini mungkin akan berkaitan dengan exersice 2 jadi nanti saya lanjutkan

# Refleksi 2

## Perasaan saya setelah membuat unit test dan code coverage

Setelah menulis unit test, saya menyadari bahwa ternyata masih banyak kesalahan (bug) dalam kode yang saya buat sebelumnya. Misalnya, masalah perbedaan nama variabel yang tidak konsisten atau kesalahan logika kecil lainnya yang tidak terlihat saat hanya menulis kode fitur.

Dengan adanya unit test, kesalahan-kesalahan tersebut bisa terdeteksi lebih dini sebelum aplikasi dijalankan secara keseluruhan. Hal ini membuat saya merasa lebih aman karena unit test bertindak sebagai "jaring pengaman" untuk memastikan setiap bagian kode berjalan sesuai ekspektasi.

### Berapa banyak unit test dalam satu class?

Tidak ada angka pasti, namun jumlahnya harus cukup untuk mencakup semua jalur logika (logical paths), termasuk skenario positif, skenario negatif, dan edge cases (kondisi batas).

### Bagaimana memastikan unit test sudah cukup?

Kita bisa menggunakan metrik Code Coverage. Ini adalah ukuran seberapa banyak baris kode kita yang sudah dieksekusi oleh test.

Di IntelliJ, kita bisa melihat ini melalui fitur "Run with Coverage".

### Apakah 100% Code Coverage berarti 0 Bug?

Tidak. Code coverage hanya menjamin bahwa baris kode tersebut pernah dijalankan saat tes, bukan berarti logikanya sudah benar untuk semua kemungkinan input atau interaksi sistem yang kompleks. Bug tetap bisa muncul pada level integrasi atau karena logika bisnis yang salah dipahami.

## Kebersihan Kode pada Functional Test Baru

Jika saya membuat functional test suite baru (misal untuk mengecek jumlah item di list) dengan menduplikasi setup dan variabel dari CreateProductFunctionalTest.java, maka kualitas kode tersebut akan menurun.

### Isu Clean Code (Code Smells)

Masalah utamanya adalah Code Duplication (Duplikasi Kode). Menuliskan prosedur setup yang sama (seperti inisialisasi driver, server port, dan base URL) berulang kali melanggar prinsip DRY (Don't Repeat Yourself).

### Dampak

Kode menjadi sulit di-maintain. Jika di masa depan ada perubahan pada konfigurasi sistem (misalnya perubahan URL dasar atau penambahan setup driver), saya harus mengubahnya di banyak file sekaligus. Ini meningkatkan risiko kesalahan manusia.

### Saran Perbaikan

- **Base Class (Inheritance)**  
  Membuat sebuah base class (misal BaseFunctionalTest) yang berisi semua variabel instans dan prosedur @BeforeEach yang umum. Test class lain cukup melakukan extends ke base class tersebut.

- **Setup Methods / Page Object Model (POM)**  
  Membungkus logika navigasi atau i

# Refleksi 3

## 1. Code Quality Issues dan Strategi Perbaikannya
Selama proses integrasi dengan **SonarCloud** dan **OSSF Scorecard**, saya menemukan beberapa peringatan (*issues*) terkait kualitas dan keamanan kode. Salah satu isu utama adalah **Security Hotspot** pada penggunaan `UUID.randomUUID()`. SonarCloud menandai bagian ini untuk mengingatkan potensi kelemahan jika generator angka acak standar digunakan untuk keperluan kriptografi atau token sesi yang sensitif.

**Strategi Perbaikan:**
Karena dalam konteks aplikasi ini `UUID` hanya digunakan sebagai penanda identitas unik produk (**Product ID**) dan tidak melibatkan data kriptografi yang kritikal, saya telah meninjau temuan tersebut dan menandainya sebagai **"Safe"** pada dashboard SonarCloud. 

Selain itu, saya juga memperbaiki masalah **Code Coverage** yang awalnya terbaca 0% di SonarCloud. Hal ini diselesaikan dengan mengaktifkan laporan JaCoCo format XML (`xml.required.set(true)`) pada file `build.gradle.kts` serta memperbaiki urutan *task* pada GitHub Actions agar laporan hasil tes di-*generate* terlebih dahulu sebelum dikirimkan ke SonarCloud. Melalui berbagai optimasi tes pada Controller dan Main class, saya berhasil mencapai **100% Code Coverage**.

## 2. Evaluasi Implementasi CI/CD
Menurut saya, alur kerja (*workflows*) yang telah saya susun menggunakan GitHub Actions sudah memenuhi prinsip **Continuous Integration (CI)** dan **Continuous Deployment (CD)** dengan sangat baik:

*   **Continuous Integration (CI):** Prinsip ini terpenuhi melalui otomatisasi setiap kali terjadi proses `push` atau `pull request`. GitHub Actions secara otomatis menjalankan proses *build*, mengeksekusi seluruh rangkaian **Unit Test** dan **Functional Test**, serta melakukan pemindaian kualitas kode melalui **SonarCloud**. Hal ini memastikan setiap perubahan kode baru tetap terjaga kualitasnya dan tidak merusak fungsi sistem yang sudah ada.
*   **Continuous Deployment (CD):** Prinsip ini terpenuhi melalui integrasi mekanisme *pull-based* ke platform PaaS (**Koyeb**). Platform tersebut secara otomatis memantau *branch* utama pada repositori GitHub. Apabila tahap CI berhasil dilewati, Koyeb akan langsung melakukan *build* ulang menggunakan **Docker image** dan memperbarui aplikasi di server publik secara otomatis tanpa perlu campur tangan manual kembali.

---
**Link Deployment (Koyeb):** [Klik di sini untuk menuju aplikasi](https://given-bertha-a-rousan-chandra-syahbunan-2406435894-97c4f5b2.koyeb.app/)

# Refleksi 4 - SOLID Principles

Setelah melakukan refactoring pada proyek ini, berikut adalah analisis mendalam mengenai penerapan prinsip **SOLID** yang telah saya lakukan:

## 1) Prinsip SOLID yang Diterapkan pada Proyek

*   **Single Responsibility Principle (SRP):**
    Saya memisahkan tanggung jawab kelas dengan membuat **CarController** yang terpisah dari **ProductController**. Sebelumnya, kodingan untuk fitur mobil menumpuk di dalam controller produk. Selain itu, saya memindahkan logika pembuatan ID unik (**UUID**) dari **CarRepository** ke dalam konstruktor model **Car**. Hal ini memastikan **Repository** hanya bertanggung jawab pada penyimpanan data, sementara **Model** bertanggung jawab atas identitas objeknya sendiri.

*   **Open-Closed Principle (OCP):**
    Saya menerapkan prinsip ini pada bagian **Functional Testing**. Saya membuat sebuah *base class* bernama **BaseFunctionalTest** yang menyimpan seluruh konfigurasi *setup server* dan *driver*. Dengan cara ini, kelas tes baru (seperti tes untuk Car) dapat memperluas (**extends**) perilaku tanpa harus mengubah kode dasar yang sudah stabil di dalam **BaseFunctionalTest**.

*   **Liskov Substitution Principle (LSP):**
    Saya menghapus hubungan pewarisan (**inheritance**) di mana sebelumnya **CarController** melakukan `extends ProductController`. Secara logika, **Mobil** bukanlah sub-tipe dari **Produk** biasa di aplikasi ini. Dengan memisahkan mereka, saya memastikan bahwa masing-masing kelas dapat berfungsi secara mandiri tanpa merusak logika kelas induk yang tidak relevan.

*   **Interface Segregation Principle (ISP):**
    Saya memisahkan antarmuka menjadi dua bagian spesifik, yaitu **ProductService** dan **CarService**. Hal ini memastikan bahwa kelas implementasi seperti **CarServiceImpl** tidak dipaksa untuk memiliki metode yang tidak mereka butuhkan (seperti metode khusus pengolahan produk biasa), sehingga kontrak antar kelas menjadi lebih ramping dan spesifik.

*   **Dependency Inversion Principle (DIP):**
    Pada tingkat **Controller**, saya memastikan kelas tersebut hanya bergantung pada **Abstraksi (Interface)**, bukan pada implementasi konkret. Saya menggunakan `@Autowired` untuk memanggil **CarService** dan **ProductService** (Interface), sehingga jika di masa depan saya ingin mengganti cara penyimpanan data (misalnya dari memori ke database), saya tidak perlu mengubah satu baris pun kodingan di dalam **Controller**.

---

## 2) Keuntungan Menerapkan Prinsip SOLID

Menerapkan prinsip SOLID memberikan banyak keuntungan pada kualitas kodingan proyek saya:

1.  **Kemudahan Pengujian (Testability):** Karena setiap kelas memiliki tanggung jawab tunggal (**SRP**), saya berhasil mencapai **100% Code Coverage** dengan lebih mudah. Contoh: Saya bisa membuat unit test khusus untuk **CarRepository** tanpa harus khawatir tes tersebut terpengaruh oleh logika di kelas produk.
2.  **Kodingan Lebih Rapi dan Terorganisir:** Dengan memisahkan interface (**ISP**), kodingan menjadi lebih bersih. Contoh: **CarServiceImpl** hanya fokus mengimplementasikan logika mobil, sehingga kodenya lebih pendek dan mudah dibaca oleh developer lain.
3.  **Fleksibilitas Tinggi:** Berkat penerapan **DIP**, aplikasi saya menjadi sangat fleksibel. Contoh: Jika asdos meminta saya mengganti **CarRepository** dari `List` menjadi database SQL, saya cukup membuat implementasi baru tanpa merusak struktur di layer **Service** atau **Controller**.

---

## 3) Kerugian Jika Tidak Menerapkan Prinsip SOLID

Jika saya mengabaikan prinsip SOLID, proyek ini akan menghadapi berbagai masalah teknis:

1.  **God Object (Spaghetti Code):** Tanpa **SRP**, file **ProductController** akan terus membengkak setiap kali fitur baru ditambah. Hal ini membuat kodingan menjadi sangat sulit dipahami dan meningkatkan risiko munculnya bug secara tidak sengaja saat melakukan perubahan kecil.
2.  **Kerapuhan Kode (Fragility):** Tanpa mematuhi **LSP**, jika saya tetap melakukan `extends ProductController`, maka setiap kali ada perubahan pada kodingan produk, fitur mobil berisiko ikut rusak (**crash**). Hal ini membuat aplikasi menjadi rapuh dan tidak stabil.
3.  **Ketergantungan yang Kaku (Tight Coupling):** Tanpa **DIP**, komponen aplikasi akan saling mengunci. Contoh: Jika Controller bergantung langsung pada kelas asli (bukan interface), maka setiap perubahan kecil pada struktur data di level bawah akan memaksa kita membongkar seluruh aplikasi dari atas sampai bawah.

## Reflection

### 1. Efektivitas Alur TDD

Menurut saya, pendekatan Test-Driven Development (TDD) cukup membantu dalam proses pengembangan. Dengan menulis test terlebih dahulu, saya dipaksa untuk memikirkan spesifikasi fitur serta kemungkinan error sebelum mulai menulis implementasi kode.

Salah satu keuntungan yang paling terasa adalah ketika melakukan refactoring atau menambahkan fitur baru. Saya bisa merasa lebih yakin karena jika ada perubahan yang menyebabkan bug, unit test akan langsung gagal pada fase **RED**, sehingga masalah tersebut bisa segera diketahui.

Namun, pada awalnya fase **RED** terasa cukup memakan waktu karena saya harus menuliskan struktur test terlebih dahulu sebelum implementasi dibuat. Ke depannya, saya perlu melatih diri untuk menulis test dengan lebih cepat dan efisien agar proses TDD dapat berjalan lebih lancar tanpa mengurangi kualitas pengujian.

---

### 2. Implementasi Prinsip F.I.R.S.T pada Unit Test

Dalam pembuatan unit test, saya berusaha mengikuti prinsip **F.I.R.S.T** sebagai panduan agar test yang dibuat tetap berkualitas.

**Fast**  
Test dijalankan dengan cepat karena menggunakan Mockito dan MockMvc tanpa bergantung pada database eksternal atau jaringan. Dengan demikian, test dapat dijalankan berulang kali tanpa memperlambat proses development.

**Independent**  
Setiap test berjalan secara terpisah dan tidak bergantung pada test lainnya. Saya menggunakan anotasi `@BeforeEach` untuk memastikan setiap test memiliki objek `Order` yang baru, sehingga kondisi awal selalu konsisten.

**Repeatable**  
Test bersifat deterministik, sehingga hasilnya akan tetap sama setiap kali dijalankan. Hasil pengujian tidak dipengaruhi oleh urutan eksekusi test maupun lingkungan tempat test dijalankan.

**Self-validating**  
Test memberikan hasil yang jelas secara otomatis melalui assertion seperti `assertEquals`, `assertThrows`, serta `verify` dari Mockito. Dengan demikian, tidak diperlukan pemeriksaan manual untuk menentukan apakah test berhasil atau gagal.

**Timely**  
Unit test ditulis sebelum implementasi fitur dibuat. Pendekatan ini mengikuti prinsip TDD, sehingga setiap kode yang ditambahkan memiliki tujuan yang jelas dan telah diverifikasi melalui test.
