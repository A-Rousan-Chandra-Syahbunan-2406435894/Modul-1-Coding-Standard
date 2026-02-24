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

Refleksi 3

1. Code Quality Issues dan Strategi Perbaikannya
Selama proses integrasi dengan SonarCloud dan OSSF Scorecard, saya menemukan beberapa peringatan (issues) terkait kualitas dan keamanan kode. Salah satu isu utama adalah Security Hotspot pada penggunaan UUID.randomUUID(). SonarCloud menandai bagian ini untuk mengingatkan potensi kelemahan jika UUID digunakan untuk cryptography atau session tokens.
Strategi Perbaikan: Karena dalam konteks aplikasi ini UUID hanya digunakan sebagai penanda identitas produk (Product ID) dan tidak melibatkan data sensitif, saya mereview teguran tersebut dan menandainya sebagai "Safe" di dashboard SonarCloud. Selain itu, saya juga memperbaiki isu Code Coverage yang bernilai 0% pada SonarCloud dengan cara menyertakan path laporan JaCoCo XML (xml.required.set(true)) dan memperbaiki urutan task pada GitHub Actions agar laporan di-generate sebelum dikirim ke SonarCloud. Dengan optimasi ini, saya berhasil mencapai 100% Code Coverage.
2. Evaluasi Implementasi CI/CD
Menurut saya, alur kerja (workflows) yang telah saya buat di GitHub Actions sudah memenuhi definisi Continuous Integration (CI) dan Continuous Deployment (CD) dengan sangat baik.
Continuous Integration (CI) terpenuhi karena setiap kali ada proses push atau pull request, GitHub Actions secara otomatis akan menjalankan build, mengeksekusi semua Unit Test dan Functional Test, serta mengirimkan laporannya ke SonarCloud untuk dianalisis kualitas kodenya. Hal ini memastikan bahwa kode baru tidak merusak sistem lama.
Continuous Deployment (CD) terpenuhi dengan adanya integrasi pull-based menggunakan platform PaaS (Koyeb/Render). Platform tersebut selalu memantau branch utama di GitHub, dan apabila status integrasi (CI) berhasil, Koyeb akan secara otomatis menarik codebase terbaru, membangun Docker image, dan mendeploy aplikasi ke server publik tanpa perlu campur tangan manual.
