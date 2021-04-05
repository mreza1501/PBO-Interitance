import java.util.ArrayList;

public class HealingItem extends Item{
    private int efek;
    private int amount;

    public HealingItem(String nama) {
        super(nama);
    }

    private void pakaiItem(){
        System.out.println("Item digunakan player");
        objGameInfo.getObjPlayer().setKesehatan(objGameInfo.getObjPlayer().getKesehatan()+this.efek); //tambahkan  efek objek ini pada player
        this.setAmount(this.getAmount()-1);  // mengurangi jumlah item saat digunakan
        if(this.amount==0){ // akan menghapus item jika jumlah mencapai 0
            objGameInfo.getObjPlayer().hapusItem(this);
        }
    }

    public ArrayList<String> getAksi() {
        //aksi dinamik tergantung ada di ruangan atau dipegang player/npc
        ArrayList<String> arrOut = new ArrayList<>();
        if (this.objRuangan == null) {
            //ada di player, ada opsi buang juga pakai item
            arrOut.add("Deskripsi Item");
            arrOut.add("Buang item");
            arrOut.add("Pakai item");
        } else {
            //ada di ruangan ada opsi ambil
            arrOut.add("Deskripsi Item");
            arrOut.add("Ambil item");
        }
        return(arrOut);
    }

    public void prosesAksi(int pil) {
        //pilihan user untuk aksi yang akan diambil
        //urutan harus sama dengan isi arrAksi
        if (pil==1) {
            System.out.println(this.getDeskripsi());
            System.out.print(this.getAmount()+"x");
        } else  if (pil==2) {//bisa ambil atau buang
            if (objRuangan==null) {
                //dipegang player, buang ke ruangan
                dibuang();
            } else {
                //ada di ruangan, diambil player
                diambil();
            }
        } else if (pil==3) {
            // player memakai item
            pakaiItem();
        }
    }


    public int getEfek() {
        return efek;
    }

    public void setEfek(int efek) {
        this.efek = efek;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
