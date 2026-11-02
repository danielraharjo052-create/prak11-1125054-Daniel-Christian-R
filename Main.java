import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.*;
import processor.PaymentProcessor;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List<PaymentStrategy> payments = new ArrayList<>();

        payments.add(new Wallet("Wallet Utama", 10000));
        payments.add(new DebitCard("123456789", 100000, "1234"));
        payments.add(new CreditCard("987654321", 5000000, "123", "1234"));

        PaymentProcessor processor = new PaymentProcessor(scanner);

        int menu;

        do {
            System.out.println("\nDynamic Payment System");
            System.out.println("1. Buat Pembayaran");
            System.out.println("2. Tambah Pembayaran");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu: ");

            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {

                case 1:
                    System.out.print("\nMasukkan jumlah yang dibayar: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    List<PaymentStrategy> availablePayments =
                            new ArrayList<>();
                    for (int i = 0; i < payments.size(); i++) {

                        if (payments.get(i).canPay(amount)) {
                            availablePayments.add(payments.get(i));
                        }
                    }

                    if (availablePayments.isEmpty()) {
                        System.out.println(
                                "Tidak ada metode pembayaran yang tersedia!"
                        );
                        break;
                    }

                    System.out.println("\nPilih Metode Pembayaran:");

                    for (int i = 0; i < availablePayments.size(); i++) {
                        System.out.println(
                                (i + 1) + ". " + availablePayments.get(i)
                        );
                    }

                    System.out.print("Pilihan: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice < 1 || choice > availablePayments.size()) {
                        System.out.println("Pilihan tidak valid!");
                        break;
                    }

                    try {
                        PaymentStrategy selectedPayment =
                                availablePayments.get(choice - 1);

                        processor.processPayment(selectedPayment, amount);

                    } catch (IllegalArgumentException e) {
                        System.out.println("Pembayaran gagal: " + e.getMessage());
                    }

                    break;

                case 2:
                    System.out.println("\n Tambah Pembayaran");
                    System.out.println("1. Cash (Wallet)");
                    System.out.println("2. Debit Card");
                    System.out.println("3. Credit Card");
                    System.out.println("4. E-Wallet");
                    System.out.print("Pilih jenis pembayaran: ");

                    int type = scanner.nextInt();
                    scanner.nextLine();

                    switch (type) {

                        case 1:
                            System.out.print("Nama Wallet: ");
                            String name = scanner.nextLine();

                            System.out.print("Saldo Cash: ");
                            double cash = scanner.nextDouble();
                            scanner.nextLine();

                            payments.add(new Wallet(name, cash));

                            System.out.println("Wallet berhasil ditambahkan!");
                            break;

                        case 2:
                            System.out.print("Nomor Kartu: ");
                            String debitNumber = scanner.nextLine();

                            System.out.print("Saldo: ");
                            double debitBalance = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("PIN: ");
                            String debitPin = scanner.nextLine();

                            payments.add(new DebitCard(
                                    debitNumber, debitBalance, debitPin
                            ));

                            System.out.println("Debit Card berhasil ditambahkan!");
                            break;

                        case 3:
                            System.out.print("Nomor Kartu: ");
                            String creditNumber = scanner.nextLine();

                            System.out.print("Limit Kredit: ");
                            double limit = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("CCV: ");
                            String ccv = scanner.nextLine();

                            System.out.print("PIN: ");
                            String creditPin = scanner.nextLine();

                            payments.add(new CreditCard(
                                    creditNumber, limit, ccv, creditPin
                            ));

                            System.out.println("Credit Card berhasil ditambahkan!");
                            break;

                        case 4:
                            System.out.print("Nomor Telepon: ");
                            String phone = scanner.nextLine();

                            System.out.print("Saldo E-Wallet: ");
                            double eBalance = scanner.nextDouble();
                            scanner.nextLine();

                            System.out.print("PIN: ");
                            String ePin = scanner.nextLine();

                            payments.add(new EWallet(
                                    phone, eBalance, ePin
                            ));

                            System.out.println("E-Wallet berhasil ditambahkan!");
                            break;

                        default:
                            System.out.println("Jenis pembayaran tidak valid!");
                    }

                    break;

                case 3:
                    System.out.println("Terima kasih telah menggunakan program!");
                    break;

                default:
                    System.out.println("Menu tidak valid!");
            }

        } while (menu != 3);

        scanner.close();
    }
}