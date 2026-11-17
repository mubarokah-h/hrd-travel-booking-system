import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

class HRDManager {
    String idHRD;
    String nama;

    public HRDManager(String idHRD, String nama) {
        this.idHRD = idHRD;
        this.nama = nama;
    }
}

class Pegawai {
    String idPegawai;
    String nama;
    String email;
    String jabatan;

    public Pegawai(String idPegawai, String nama, String email, String jabatan) {
        this.idPegawai = idPegawai;
        this.nama = nama;
        this.email = email;
        this.jabatan = jabatan;
    }

    @Override
    public String toString() {
        return idPegawai + " - " + nama + " (" + jabatan + ")";
    }
}

class JatahCuti {
    String idJatah;
    Pegawai pegawai;
    int tahun;
    int totalHakCuti;
    int sisaCuti;

    public JatahCuti(String idJatah, Pegawai pegawai, int tahun, int totalHakCuti, int sisaCuti) {
        this.idJatah = idJatah;
        this.pegawai = pegawai;
        this.tahun = tahun;
        this.totalHakCuti = totalHakCuti;
        this.sisaCuti = sisaCuti;
    }
}

class PengajuanCuti {
    String idPengajuan;
    Pegawai pegawai;
    HRDManager diprosesOleh;
    LocalDate tanggalPengajuan;
    LocalDate tanggalMulai;
    LocalDate tanggalSelesai;
    int lamaCuti;
    String status;

    public PengajuanCuti(String idPengajuan, Pegawai pegawai, HRDManager diprosesOleh,
                         LocalDate tanggalPengajuan, LocalDate tanggalMulai,
                         LocalDate tanggalSelesai, int lamaCuti, String status) {
        this.idPengajuan = idPengajuan;
        this.pegawai = pegawai;
        this.diprosesOleh = diprosesOleh;
        this.tanggalPengajuan = tanggalPengajuan;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.lamaCuti = lamaCuti;
        this.status = status;
    }
}

class RiwayatCuti {
    String idRiwayat;
    Pegawai pegawai;
    int tahun;
    int totalCuti;

    public RiwayatCuti(String idRiwayat, Pegawai pegawai, int tahun, int totalCuti) {
        this.idRiwayat = idRiwayat;
        this.pegawai = pegawai;
        this.tahun = tahun;
        this.totalCuti = totalCuti;
    }
}

class Pengguna {
    String idPengguna;
    String nama;
    String email;
    Profil profil;

    public Pengguna(String idPengguna, String nama, String email) {
        this.idPengguna = idPengguna;
        this.nama = nama;
        this.email = email;
    }
}

class MemberFrequentTraveller extends Pengguna {
    String idMember;
    String nomorMember;
    String statusMember;

    public MemberFrequentTraveller(String idPengguna, String nama, String email,
                                   String idMember, String nomorMember, String statusMember) {
        super(idPengguna, nama, email);
        this.idMember = idMember;
        this.nomorMember = nomorMember;
        this.statusMember = statusMember;
    }
}

class ManajerTravel extends Pengguna {
    String idManajer;

    public ManajerTravel(String idPengguna, String nama, String email, String idManajer) {
        super(idPengguna, nama, email);
        this.idManajer = idManajer;
    }
}

class Pengunjung extends Pengguna {
    String idPengunjung;
    List<Tiket> akunTiket = new ArrayList<>();

    public Pengunjung(String idPengguna, String nama, String email, String idPengunjung) {
        super(idPengguna, nama, email);
        this.idPengunjung = idPengunjung;
    }
}

class Profil {
    String idProfil;
    String namaLengkap;
    LocalDate tanggalLahir;
    String nomorIdentitas;
    String alamat;
    boolean statusKelengkapan;

    public Profil(String idProfil, String namaLengkap, LocalDate tanggalLahir,
                  String nomorIdentitas, String alamat) {
        this.idProfil = idProfil;
        this.namaLengkap = namaLengkap;
        this.tanggalLahir = tanggalLahir;
        this.nomorIdentitas = nomorIdentitas;
        this.alamat = alamat;
        cekKelengkapan();
    }

    public boolean cekKelengkapan() {
        this.statusKelengkapan = (namaLengkap != null && !namaLengkap.trim().isEmpty())
                && (tanggalLahir != null)
                && (nomorIdentitas != null && !nomorIdentitas.trim().isEmpty())
                && (alamat != null && !alamat.trim().isEmpty());
        return this.statusKelengkapan;
    }
}

class Maskapai {
    String idMaskapai;
    String namaMaskapai;

    public Maskapai(String idMaskapai, String namaMaskapai) {
        this.idMaskapai = idMaskapai;
        this.namaMaskapai = namaMaskapai;
    }
}

class Penerbangan {
    String idPenerbangan;
    Maskapai maskapai;
    String lokasiAsal;
    String lokasiTujuan;
    LocalDate tanggalBerangkat;
    LocalTime jamBerangkat;
    double harga;
    int jumlahKursi;

    public Penerbangan(String idPenerbangan, Maskapai maskapai, String lokasiAsal,
                       String lokasiTujuan, LocalDate tanggalBerangkat,
                       LocalTime jamBerangkat, double harga, int jumlahKursi) {
        this.idPenerbangan = idPenerbangan;
        this.maskapai = maskapai;
        this.lokasiAsal = lokasiAsal;
        this.lokasiTujuan = lokasiTujuan;
        this.tanggalBerangkat = tanggalBerangkat;
        this.jamBerangkat = jamBerangkat;
        this.harga = harga;
        this.jumlahKursi = jumlahKursi;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | %s -> %s | Tgl: %s Jam: %s | Rp%,.0f (Sisa Kursi: %d)",
                idPenerbangan, maskapai.namaMaskapai, lokasiAsal, lokasiTujuan,
                tanggalBerangkat, jamBerangkat, harga, jumlahKursi);
    }
}

class Pemesanan {
    String idPemesanan;
    String kodeBookingReference;
    Pengguna pemesan;
    ManajerTravel diprosesOleh;
    Penerbangan penerbangan;
    LocalDate tanggalPemesanan;
    String statusPemesanan;
    double totalHarga;

    public Pemesanan(String idPemesanan, String kodeBookingReference, Pengguna pemesan,
                     Penerbangan penerbangan, String statusPemesanan) {
        this.idPemesanan = idPemesanan;
        this.kodeBookingReference = kodeBookingReference;
        this.pemesan = pemesan;
        this.penerbangan = penerbangan;
        this.tanggalPemesanan = LocalDate.now();
        this.statusPemesanan = statusPemesanan;
        this.totalHarga = penerbangan.harga;
    }
}

class Tiket {
    String idTiket;
    String nomorTiket;
    Pemesanan pemesanan;
    String statusTiket;

    public Tiket(String idTiket, String nomorTiket, Pemesanan pemesanan, String statusTiket) {
        this.idTiket = idTiket;
        this.nomorTiket = nomorTiket;
        this.pemesanan = pemesanan;
        this.statusTiket = statusTiket;
    }
}

class NotifikasiEmail {
    String idNotifikasi;
    String alamatEmail;
    LocalDateTime waktuKirim;
    String statusPengiriman;

    public NotifikasiEmail(String idNotifikasi, String alamatEmail, String statusPengiriman) {
        this.idNotifikasi = idNotifikasi;
        this.alamatEmail = alamatEmail;
        this.waktuKirim = LocalDateTime.now();
        this.statusPengiriman = statusPengiriman;
    }
}

class CutiController {
    List<PengajuanCuti> databasePengajuan = new ArrayList<>();
    List<RiwayatCuti> databaseRiwayat = new ArrayList<>();
    List<JatahCuti> databaseJatah = new ArrayList<>();

    public String requestDataPeriode(int bulan, int tahun) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== HASIL PENCARIAN PENGAJUAN CUTI (Bulan ").append(bulan).append(" / ").append(tahun).append(") ===\n");
        List<PengajuanCuti> hasil = databasePengajuan.stream()
                .filter(p -> p.tanggalMulai.getMonthValue() == bulan && p.tanggalMulai.getYear() == tahun)
                .collect(Collectors.toList());

        if (hasil.isEmpty()) {
            sb.append("Pesan: Data pengajuan cuti tidak tersedia pada periode tersebut.\n");
        } else {
            for (PengajuanCuti pc : hasil) {
                sb.append(String.format("• Pegawai: %s (%s)\n  Periode: %s s/d %s (%d hari) | Status: %s\n",
                        pc.pegawai.nama, pc.pegawai.jabatan, pc.tanggalMulai, pc.tanggalSelesai, pc.lamaCuti, pc.status));
            }
        }
        return sb.toString();
    }

    public String requestRiwayatCuti(String idPegawai, int tahun) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== RIWAYAT & SISA JATAH CUTI (ID: ").append(idPegawai).append(" - Tahun ").append(tahun).append(") ===\n");
        databaseRiwayat.stream()
                .filter(r -> r.pegawai.idPegawai.equals(idPegawai) && r.tahun == tahun)
                .findFirst()
                .ifPresentOrElse(
                        r -> sb.append("• Total Cuti Terpakai: ").append(r.totalCuti).append(" hari\n"),
                        () -> sb.append("• Total Cuti Terpakai: 0 hari\n")
                );

        databaseJatah.stream()
                .filter(j -> j.pegawai.idPegawai.equals(idPegawai) && j.tahun == tahun)
                .findFirst()
                .ifPresentOrElse(
                        j -> sb.append("• Total Hak Cuti Tahunan: ").append(j.totalHakCuti)
                               .append(" hari\n• Sisa Jatah Cuti: ").append(j.sisaCuti).append(" hari\n"),
                        () -> sb.append("• Data jatah cuti tidak ditemukan.\n")
                );
        return sb.toString();
    }
}

class PencarianPenerbanganController {
    List<Penerbangan> databasePenerbangan = new ArrayList<>();

    public List<Penerbangan> cariPenerbangan(String asal, String tujuan, LocalDate tanggal) {
        return databasePenerbangan.stream()
                .filter(p -> p.lokasiAsal.equalsIgnoreCase(asal.trim())
                        && p.lokasiTujuan.equalsIgnoreCase(tujuan.trim())
                        && p.tanggalBerangkat.equals(tanggal))
                .collect(Collectors.toList());
    }

    public String cariBeberapaTujuan(List<String> daftarTujuan) {
        StringBuilder sb = new StringBuilder("=== HASIL PENCARIAN BEBERAPA TUJUAN (US3) ===\n");
        List<Penerbangan> hasil = databasePenerbangan.stream()
                .filter(p -> daftarTujuan.stream().anyMatch(t -> t.trim().equalsIgnoreCase(p.lokasiTujuan)))
                .collect(Collectors.toList());

        if (hasil.isEmpty()) {
            sb.append("Pesan: Penerbangan untuk tujuan tersebut tidak tersedia.\n");
        } else {
            hasil.forEach(p -> sb.append("• ").append(p).append("\n"));
        }
        return sb.toString();
    }

    public String cariMultiDestinasi(MemberFrequentTraveller member, LocalDate tanggal, String asal, List<String> tujuanMulti) {
        StringBuilder sb = new StringBuilder("=== PENCARIAN MULTI-DESTINASI (US4 - Member: " + member.nama + ") ===\n");
        if (tanggal == null || asal == null || asal.trim().isEmpty() || tujuanMulti == null || tujuanMulti.isEmpty()) {
            return sb.append("Pesan Kesalahan: Data pencarian belum lengkap.\n").toString();
        }

        List<Penerbangan> hasil = databasePenerbangan.stream()
                .filter(p -> p.tanggalBerangkat.equals(tanggal)
                        && (p.lokasiAsal.equalsIgnoreCase(asal.trim())
                        || tujuanMulti.stream().anyMatch(t -> t.trim().equalsIgnoreCase(p.lokasiTujuan))))
                .collect(Collectors.toList());

        if (hasil.isEmpty()) {
            sb.append("Pesan: Penerbangan multi-destinasi tidak tersedia.\n");
        } else {
            hasil.forEach(p -> sb.append("• ").append(p).append("\n"));
        }
        return sb.toString();
    }

    public String bandingkanHargaPenerbangan(String tujuan, LocalDate tanggal, LocalTime waktuMulai) {
        StringBuilder sb = new StringBuilder("=== PERBANDINGAN HARGA PENERBANGAN (US6) ===\n");
        List<Penerbangan> hasil = databasePenerbangan.stream()
                .filter(p -> p.lokasiTujuan.equalsIgnoreCase(tujuan.trim())
                        && p.tanggalBerangkat.equals(tanggal)
                        && !p.jamBerangkat.isBefore(waktuMulai))
                .sorted(Comparator.comparingDouble(p -> p.harga))
                .collect(Collectors.toList());

        if (hasil.isEmpty()) {
            sb.append("Pesan: Penerbangan tidak tersedia untuk kriteria tersebut.\n");
        } else {
            int rank = 1;
            for (Penerbangan p : hasil) {
                sb.append("Opsi #").append(rank++).append(": ").append(p).append("\n");
            }
        }
        return sb.toString();
    }
}

class ProfilController {
    public String updateProfil(MemberFrequentTraveller member, String namaLengkap, LocalDate tglLahir, String noIdentitas, String alamat) {
        member.profil = new Profil("PRF-" + member.idMember, namaLengkap, tglLahir, noIdentitas, alamat);
        return "Konfirmasi: Profil untuk " + member.nama + " berhasil diperbarui.\nStatus Kelengkapan: "
                + (member.profil.statusKelengkapan ? "LENGKAP" : "BELUM LENGKAP");
    }

    public String validasiTiket(MemberFrequentTraveller member, Tiket tiket) {
        if (member.profil == null || !member.profil.cekKelengkapan()) {
            return "VALIDASI DITOLAK: Data profil tidak lengkap! Harap lengkapi profil terlebih dahulu.";
        }
        tiket.statusTiket = "TERVALIDASI";
        return "VALIDASI BERHASIL: Tiket " + tiket.nomorTiket + " atas nama " + member.profil.namaLengkap + " telah tervalidasi.";
    }
}

class BookingController {
    List<Pemesanan> databasePemesanan = new ArrayList<>();
    List<Tiket> databaseTiket = new ArrayList<>();

    public Pemesanan pesanTiket(Pengguna pengguna, Penerbangan penerbangan, String statusAwal) {
        String idPemesanan = "ORD-" + (databasePemesanan.size() + 1);
        String kodeBooking = "BK-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Pemesanan p = new Pemesanan(idPemesanan, kodeBooking, pengguna, penerbangan, statusAwal);
        penerbangan.jumlahKursi--;
        databasePemesanan.add(p);
        return p;
    }

    public String batalkanTiket(String kodeBooking) {
        for (Pemesanan p : databasePemesanan) {
            if (p.kodeBookingReference.equalsIgnoreCase(kodeBooking.trim())) {
                if ("cancelled".equalsIgnoreCase(p.statusPemesanan)) {
                    return "Informasi: Pesanan " + kodeBooking + " sudah berstatus dibatalkan.";
                }
                p.statusPemesanan = "cancelled";
                p.penerbangan.jumlahKursi++;
                return "Konfirmasi: Pesanan dengan Kode Booking " + p.kodeBookingReference + " berhasil dibatalkan.";
            }
        }
        return "Pesan Kesalahan: Kode booking tidak ditemukan.";
    }

    public String pantauStatusPemesanan(String filterStatus) {
        StringBuilder sb = new StringBuilder("=== DAFTAR STATUS PEMESANAN (Filter: " + filterStatus + ") ===\n");
        List<Pemesanan> hasil = databasePemesanan.stream()
                .filter(p -> "SEMUA".equalsIgnoreCase(filterStatus) || p.statusPemesanan.equalsIgnoreCase(filterStatus))
                .collect(Collectors.toList());

        if (hasil.isEmpty()) {
            sb.append("Informasi: Data pemesanan dengan status tersebut tidak ditemukan.\n");
        } else {
            for (Pemesanan p : hasil) {
                sb.append(String.format("• [%s] Pemesan: %s | %s -> %s | Status: %s\n",
                        p.kodeBookingReference, p.pemesan.nama,
                        p.penerbangan.lokasiAsal, p.penerbangan.lokasiTujuan, p.statusPemesanan.toUpperCase()));
            }
        }
        return sb.toString();
    }

    public String pesanTiketUntukPengunjung(ManajerTravel manajer, String namaPengunjung, String emailPengunjung, Penerbangan penerbangan) {
        if (namaPengunjung == null || namaPengunjung.trim().isEmpty()
                || emailPengunjung == null || emailPengunjung.trim().isEmpty()) {
            return "Pesan Kesalahan: Data pengunjung tidak lengkap (Nama dan Email wajib diisi).";
        }

        Pengunjung pengunjung = new Pengunjung("USR-" + UUID.randomUUID().toString().substring(0, 4),
                namaPengunjung.trim(), emailPengunjung.trim(), "VST-" + (databasePemesanan.size() + 1));

        String idPemesanan = "ORD-" + (databasePemesanan.size() + 1);
        String kodeBooking = "BK-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        Pemesanan pemesanan = new Pemesanan(idPemesanan, kodeBooking, pengunjung, penerbangan, "confirmed");
        pemesanan.diprosesOleh = manajer;
        penerbangan.jumlahKursi--;
        databasePemesanan.add(pemesanan);

        Tiket eTicket = new Tiket("TKT-" + (databaseTiket.size() + 1), "ETKT-" + kodeBooking, pemesanan, "ISSUED");
        databaseTiket.add(eTicket);
        pengunjung.akunTiket.add(eTicket);

        NotifikasiEmail notif = new NotifikasiEmail("NTF-" + databaseTiket.size(), pengunjung.email, "TERKIRIM");

        return String.format(
                "PEMESANAN PENGUNJUNG BERHASIL (US8)\n" +
                "• Diproses Oleh : %s\n" +
                "• Pengunjung    : %s (%s)\n" +
                "• Kode Booking  : %s\n" +
                "• Nomor E-Ticket: %s (Terhubung ke Akun Pengunjung)\n" +
                "• Notifikasi    : E-Ticket %s ke %s pada %s",
                manajer.nama, pengunjung.nama, pengunjung.email, kodeBooking,
                eTicket.nomorTiket, notif.statusPengiriman, notif.alamatEmail, notif.waktuKirim.toLocalTime().withNano(0)
        );
    }

    public String otorisasiTiket(String kodeBookingReference) {
        for (Pemesanan p : databasePemesanan) {
            if (p.kodeBookingReference.equalsIgnoreCase(kodeBookingReference.trim())) {
                return String.format(
                        "OTORISASI VALID (US9)\n" +
                        "• Kode Booking : %s\n" +
                        "• Pemesan      : %s\n" +
                        "• Rute         : %s -> %s (%s)\n" +
                        "• Status Tiket : %s",
                        p.kodeBookingReference, p.pemesan.nama,
                        p.penerbangan.lokasiAsal, p.penerbangan.lokasiTujuan,
                        p.penerbangan.tanggalBerangkat, p.statusPemesanan.toUpperCase()
                );
            }
        }
        return "PESAN KESALAHAN (US9): Kode booking reference '" + kodeBookingReference + "' tidak ditemukan atau tidak valid.";
    }
}

public class ConceptualModelGUIApp extends JFrame {
    private final CutiController cutiCtrl = new CutiController();
    private final PencarianPenerbanganController cariCtrl = new PencarianPenerbanganController();
    private final ProfilController profilCtrl = new ProfilController();
    private final BookingController bookingCtrl = new BookingController();

    private HRDManager hrdManager;
    private Pengguna penggunaReguler;
    private MemberFrequentTraveller memberFT;
    private ManajerTravel manajerTravel;
    private Tiket tiketMemberDummy;

    public ConceptualModelGUIApp() {
        initSampleData();

        setTitle("Sistem Informasi Conceptual Model (US1 - US9)");
        setSize(920, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 12));

        tabbedPane.addTab("US1: Cuti Pegawai (HRD)", createPanelUS1());
        tabbedPane.addTab("US2 & US3: Cari & Pesan Tiket", createPanelUS2US3());
        tabbedPane.addTab("US4, US5, US6: Member FT", createPanelUS4US5US6());
        tabbedPane.addTab("US7, US8, US9: Manajer Travel", createPanelUS7US8US9());

        add(tabbedPane);
    }

    private void initSampleData() {
        hrdManager = new HRDManager("HRD01", "Ibu Ratna");
        Pegawai p1 = new Pegawai("PEG01", "Budi Santoso", "budi@perusahaan.com", "Staff IT");
        Pegawai p2 = new Pegawai("PEG02", "Rina Wijaya", "rina@perusahaan.com", "Analis Keuangan");

        cutiCtrl.databaseJatah.add(new JatahCuti("JT01", p1, 2026, 12, 9));
        cutiCtrl.databaseRiwayat.add(new RiwayatCuti("RW01", p1, 2026, 3));
        cutiCtrl.databasePengajuan.add(new PengajuanCuti("CT01", p1, hrdManager,
                LocalDate.of(2026, 11, 1), LocalDate.of(2026, 11, 10), LocalDate.of(2026, 11, 12), 3, "Disetujui"));

        cutiCtrl.databaseJatah.add(new JatahCuti("JT02", p2, 2026, 12, 10));
        cutiCtrl.databaseRiwayat.add(new RiwayatCuti("RW02", p2, 2026, 2));
        cutiCtrl.databasePengajuan.add(new PengajuanCuti("CT02", p2, hrdManager,
                LocalDate.of(2026, 11, 5), LocalDate.of(2026, 11, 20), LocalDate.of(2026, 11, 21), 2, "Menunggu"));

        Maskapai garuda = new Maskapai("GA", "Garuda Indonesia");
        Maskapai citilink = new Maskapai("QG", "Citilink");
        Maskapai airasia = new Maskapai("QZ", "AirAsia");

        LocalDate tgl = LocalDate.of(2026, 12, 20);
        Penerbangan fl1 = new Penerbangan("FL01", garuda, "Jakarta", "Denpasar", tgl, LocalTime.of(8, 0), 1550000, 20);
        Penerbangan fl2 = new Penerbangan("FL02", citilink, "Jakarta", "Denpasar", tgl, LocalTime.of(10, 30), 1150000, 15);
        Penerbangan fl3 = new Penerbangan("FL03", airasia, "Jakarta", "Denpasar", tgl, LocalTime.of(14, 15), 980000, 18);
        Penerbangan fl4 = new Penerbangan("FL04", garuda, "Denpasar", "Surabaya", tgl, LocalTime.of(16, 0), 920000, 12);

        cariCtrl.databasePenerbangan.addAll(List.of(fl1, fl2, fl3, fl4));

        penggunaReguler = new Pengguna("USR01", "Andi Pratama", "andi@email.com");
        memberFT = new MemberFrequentTraveller("USR02", "Siti Aminah", "siti@email.com", "MEM01", "FT-998877", "Aktif");
        manajerTravel = new ManajerTravel("USR03", "Pak Hendra", "hendra@travel.com", "MGR01");

        Pemesanan ord1 = bookingCtrl.pesanTiket(penggunaReguler, fl1, "waiting for the payment");
        Pemesanan ord2 = bookingCtrl.pesanTiket(memberFT, fl2, "confirmed");
        tiketMemberDummy = new Tiket("TKT-M01", "ETKT-" + ord2.kodeBookingReference, ord2, "PENDING");
        bookingCtrl.databaseTiket.add(tiketMemberDummy);
    }

    private JPanel createPanelUS1() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JTextArea outputArea = createOutputArea();

        JPanel topControls = new JPanel(new GridLayout(1, 2, 10, 10));

        JPanel periodePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        periodePanel.setBorder(new TitledBorder("Cek Jadwal Cuti per Periode (US1)"));
        JComboBox<Integer> cbBulan = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        cbBulan.setSelectedItem(11);
        JTextField txtTahun = new JTextField("2026", 5);
        JButton btnCekPeriode = new JButton("Tampilkan Jadwal");
        btnCekPeriode.addActionListener(e -> {
            int bln = (Integer) cbBulan.getSelectedItem();
            int thn = Integer.parseInt(txtTahun.getText().trim());
            outputArea.setText(cutiCtrl.requestDataPeriode(bln, thn));
        });
        periodePanel.add(new JLabel("Bulan:"));
        periodePanel.add(cbBulan);
        periodePanel.add(new JLabel("Tahun:"));
        periodePanel.add(txtTahun);
        periodePanel.add(btnCekPeriode);

        JPanel riwayatPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        riwayatPanel.setBorder(new TitledBorder("Cek Riwayat & Sisa Cuti Pegawai (US1)"));
        JComboBox<String> cbPegawai = new JComboBox<>(new String[]{"PEG01", "PEG02"});
        JButton btnCekRiwayat = new JButton("Lihat Riwayat & Sisa");
        btnCekRiwayat.addActionListener(e -> {
            String idPeg = (String) cbPegawai.getSelectedItem();
            outputArea.setText(cutiCtrl.requestRiwayatCuti(idPeg, 2026));
        });
        riwayatPanel.add(new JLabel("ID Pegawai:"));
        riwayatPanel.add(cbPegawai);
        riwayatPanel.add(btnCekRiwayat);

        topControls.add(periodePanel);
        topControls.add(riwayatPanel);

        panel.add(topControls, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelUS2US3() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JTextArea outputArea = createOutputArea();
        JComboBox<Penerbangan> cbHasilPenerbangan = new JComboBox<>();

        JPanel formContainer = new JPanel(new GridLayout(3, 1, 8, 8));

        JPanel us2Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us2Panel.setBorder(new TitledBorder("US2: Cari Jadwal & Pesan Tiket (Pengguna: Andi Pratama)"));
        JTextField txtAsal = new JTextField("Jakarta", 8);
        JTextField txtTujuan = new JTextField("Denpasar", 8);
        JTextField txtTanggal = new JTextField("2026-12-20", 9);
        JButton btnCari = new JButton("Cari Jadwal");
        JButton btnPesan = new JButton("Pesan Terpilih");

        btnCari.addActionListener(e -> {
            try {
                LocalDate tgl = LocalDate.parse(txtTanggal.getText().trim());
                List<Penerbangan> list = cariCtrl.cariPenerbangan(txtAsal.getText(), txtTujuan.getText(), tgl);
                cbHasilPenerbangan.removeAllItems();
                if (list.isEmpty()) {
                    outputArea.setText("Pesan: Penerbangan tidak tersedia.");
                } else {
                    StringBuilder sb = new StringBuilder("=== JADWAL PENERBANGAN TERSEDIA (US2) ===\n");
                    for (Penerbangan p : list) {
                        cbHasilPenerbangan.addItem(p);
                        sb.append("• ").append(p).append("\n");
                    }
                    outputArea.setText(sb.toString());
                }
            } catch (Exception ex) {
                outputArea.setText("Format tanggal harus YYYY-MM-DD.");
            }
        });

        btnPesan.addActionListener(e -> {
            Penerbangan terpilih = (Penerbangan) cbHasilPenerbangan.getSelectedItem();
            if (terpilih == null) {
                outputArea.setText("Silakan cari dan pilih penerbangan terlebih dahulu.");
                return;
            }
            Pemesanan p = bookingCtrl.pesanTiket(penggunaReguler, terpilih, "booked");
            outputArea.setText(String.format(
                    "PEMESANAN BERHASIL (US2)\n• Kode Booking: %s\n• Pemesan: %s\n• Penerbangan: %s\n• Status: %s",
                    p.kodeBookingReference, p.pemesan.nama, p.penerbangan, p.statusPemesanan
            ));
        });

        us2Panel.add(new JLabel("Asal:"));
        us2Panel.add(txtAsal);
        us2Panel.add(new JLabel("Tujuan:"));
        us2Panel.add(txtTujuan);
        us2Panel.add(new JLabel("Tgl:"));
        us2Panel.add(txtTanggal);
        us2Panel.add(btnCari);
        us2Panel.add(cbHasilPenerbangan);
        us2Panel.add(btnPesan);

        JPanel cancelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cancelPanel.setBorder(new TitledBorder("US2: Pembatalan Tiket"));
        JTextField txtKodeBatal = new JTextField(10);
        JButton btnBatal = new JButton("Batalkan Pesanan");
        btnBatal.addActionListener(e -> outputArea.setText(bookingCtrl.batalkanTiket(txtKodeBatal.getText())));
        cancelPanel.add(new JLabel("Masukkan Kode Booking:"));
        cancelPanel.add(txtKodeBatal);
        cancelPanel.add(btnBatal);

        JPanel us3Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us3Panel.setBorder(new TitledBorder("US3: Pencarian Beberapa Tujuan Sekaligus (Pisahkan dengan koma)"));
        JTextField txtMultiTujuan = new JTextField("Denpasar, Surabaya", 22);
        JButton btnCariMulti = new JButton("Cari Beberapa Tujuan");
        btnCariMulti.addActionListener(e -> {
            List<String> tujuanList = List.of(txtMultiTujuan.getText().split(","));
            outputArea.setText(cariCtrl.cariBeberapaTujuan(tujuanList));
        });
        us3Panel.add(new JLabel("Daftar Tujuan:"));
        us3Panel.add(txtMultiTujuan);
        us3Panel.add(btnCariMulti);

        formContainer.add(us2Panel);
        formContainer.add(cancelPanel);
        formContainer.add(us3Panel);

        panel.add(formContainer, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelUS4US5US6() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JTextArea outputArea = createOutputArea();
        JPanel formContainer = new JPanel(new GridLayout(3, 1, 8, 8));

        JPanel us4Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us4Panel.setBorder(new TitledBorder("US4: Pencarian Multi-Destinasi (Member: Siti Aminah)"));
        JTextField txtTgl = new JTextField("2026-12-20", 9);
        JTextField txtAsal = new JTextField("Jakarta", 8);
        JTextField txtDestinasi = new JTextField("Denpasar, Surabaya", 16);
        JButton btnMultiDest = new JButton("Cari Multi-Destinasi");
        btnMultiDest.addActionListener(e -> {
            try {
                LocalDate tgl = LocalDate.parse(txtTgl.getText().trim());
                List<String> listTujuan = List.of(txtDestinasi.getText().split(","));
                outputArea.setText(cariCtrl.cariMultiDestinasi(memberFT, tgl, txtAsal.getText(), listTujuan));
            } catch (Exception ex) {
                outputArea.setText("Pesan Kesalahan: Data pencarian belum lengkap atau format tanggal salah.");
            }
        });
        us4Panel.add(new JLabel("Tgl:"));
        us4Panel.add(txtTgl);
        us4Panel.add(new JLabel("Asal:"));
        us4Panel.add(txtAsal);
        us4Panel.add(new JLabel("Tujuan:"));
        us4Panel.add(txtDestinasi);
        us4Panel.add(btnMultiDest);

        JPanel us5Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us5Panel.setBorder(new TitledBorder("US5: Update Data Personal & Validasi Tiket (" + tiketMemberDummy.nomorTiket + ")"));
        JTextField txtNama = new JTextField("Siti Aminah", 10);
        JTextField txtNoId = new JTextField("3573011405980001", 12);
        JTextField txtAlamat = new JTextField("Jl. Veteran, Malang", 12);
        JButton btnUpdateProfil = new JButton("Simpan Profil");
        JButton btnValidasiTiket = new JButton("Validasi Tiket");

        btnUpdateProfil.addActionListener(e -> outputArea.setText(
                profilCtrl.updateProfil(memberFT, txtNama.getText(), LocalDate.of(1998, 5, 14), txtNoId.getText(), txtAlamat.getText())
        ));
        btnValidasiTiket.addActionListener(e -> outputArea.setText(
                profilCtrl.validasiTiket(memberFT, tiketMemberDummy)
        ));

        us5Panel.add(new JLabel("Nama:"));
        us5Panel.add(txtNama);
        us5Panel.add(new JLabel("No. ID:"));
        us5Panel.add(txtNoId);
        us5Panel.add(new JLabel("Alamat:"));
        us5Panel.add(txtAlamat);
        us5Panel.add(btnUpdateProfil);
        us5Panel.add(btnValidasiTiket);

        JPanel us6Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us6Panel.setBorder(new TitledBorder("US6: Bandingkan Harga Penerbangan"));
        JTextField txtTujuanBanding = new JTextField("Denpasar", 10);
        JTextField txtTglBanding = new JTextField("2026-12-20", 9);
        JTextField txtJamMulai = new JTextField("07:00", 5);
        JButton btnBanding = new JButton("Bandingkan Harga");
        btnBanding.addActionListener(e -> {
            try {
                LocalDate tgl = LocalDate.parse(txtTglBanding.getText().trim());
                LocalTime jam = LocalTime.parse(txtJamMulai.getText().trim());
                outputArea.setText(cariCtrl.bandingkanHargaPenerbangan(txtTujuanBanding.getText(), tgl, jam));
            } catch (Exception ex) {
                outputArea.setText("Format tanggal (YYYY-MM-DD) atau jam (HH:MM) tidak sesuai.");
            }
        });
        us6Panel.add(new JLabel("Tujuan:"));
        us6Panel.add(txtTujuanBanding);
        us6Panel.add(new JLabel("Tgl:"));
        us6Panel.add(txtTglBanding);
        us6Panel.add(new JLabel("Mulai Jam:"));
        us6Panel.add(txtJamMulai);
        us6Panel.add(btnBanding);

        formContainer.add(us4Panel);
        formContainer.add(us5Panel);
        formContainer.add(us6Panel);

        panel.add(formContainer, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createPanelUS7US8US9() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(12, 12, 12, 12));

        JTextArea outputArea = createOutputArea();
        JPanel formContainer = new JPanel(new GridLayout(3, 1, 8, 8));

        JPanel us7Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us7Panel.setBorder(new TitledBorder("US7: Pemantauan & Filter Status Pemesanan"));
        JComboBox<String> cbStatus = new JComboBox<>(new String[]{"SEMUA", "confirmed", "booked", "waiting for the payment", "cancelled"});
        JButton btnFilterStatus = new JButton("Tampilkan Pemesanan");
        btnFilterStatus.addActionListener(e -> outputArea.setText(
                bookingCtrl.pantauStatusPemesanan((String) cbStatus.getSelectedItem())
        ));
        us7Panel.add(new JLabel("Filter Status:"));
        us7Panel.add(cbStatus);
        us7Panel.add(btnFilterStatus);

        JPanel us8Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us8Panel.setBorder(new TitledBorder("US8: Pesan Tiket untuk Pengunjung & Kirim E-Ticket"));
        JTextField txtNamaPengunjung = new JTextField("Dimas Anggara", 11);
        JTextField txtEmailPengunjung = new JTextField("dimas@email.com", 13);
        JComboBox<Penerbangan> cbPenerbanganUS8 = new JComboBox<>(cariCtrl.databasePenerbangan.toArray(new Penerbangan[0]));
        JButton btnPesanPengunjung = new JButton("Proses & Kirim E-Ticket");
        btnPesanPengunjung.addActionListener(e -> {
            Penerbangan fl = (Penerbangan) cbPenerbanganUS8.getSelectedItem();
            outputArea.setText(bookingCtrl.pesanTiketUntukPengunjung(
                    manajerTravel, txtNamaPengunjung.getText(), txtEmailPengunjung.getText(), fl
            ));
        });
        us8Panel.add(new JLabel("Nama:"));
        us8Panel.add(txtNamaPengunjung);
        us8Panel.add(new JLabel("Email:"));
        us8Panel.add(txtEmailPengunjung);
        us8Panel.add(cbPenerbanganUS8);
        us8Panel.add(btnPesanPengunjung);

        JPanel us9Panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        us9Panel.setBorder(new TitledBorder("US9: Otorisasi Tiket dengan Kode Booking Reference"));
        JTextField txtKodeOtorisasi = new JTextField(12);
        JButton btnOtorisasi = new JButton("Validasi & Otorisasi");
        btnOtorisasi.addActionListener(e -> outputArea.setText(
                bookingCtrl.otorisasiTiket(txtKodeOtorisasi.getText())
        ));
        us9Panel.add(new JLabel("Kode Booking Reference:"));
        us9Panel.add(txtKodeOtorisasi);
        us9Panel.add(btnOtorisasi);

        formContainer.add(us7Panel);
        formContainer.add(us8Panel);
        formContainer.add(us9Panel);

        panel.add(formContainer, BorderLayout.NORTH);
        panel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        return panel;
    }

    private JTextArea createOutputArea() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 13));
        area.setMargin(new Insets(10, 10, 10, 10));
        return area;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new ConceptualModelGUIApp().setVisible(true);
        });
    }
}