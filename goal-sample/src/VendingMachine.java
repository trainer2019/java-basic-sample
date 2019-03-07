import java.util.HashMap;
import java.util.Map;

/**
 * ★☆ Java基礎講座修了レベル ☆★
 *
 * 自動販売機のようにお金を入れて商品を買うプログラムです。
 * コマンドライン引数から投入したお金と購入する飲み物を入力すると動きます。
 *
 * <引数例>100 50 cola
 */
class VendingMachine {

    // 自動販売機で買える飲み物一覧
    private static final Map<String, Integer> drinks = new HashMap<>() {
        {
            put("coffee", 100);
            put("orange", 120);
            put("cola", 130);
        }
    };

    public static void main(String[] args) {

        System.out.println("[DEBUG] ========== 購入処理：開始 ==========");

        // 引数の値は必ず2つ以上必要です。足りない場合は強制終了。
        if (args.length < 2) {
            System.out.println("[ERROR] 引数が正しくありません。");
            System.exit(0); // 強制終了させる
        }

        int number = args.length - 1; // 投入された硬貨の枚数。最後尾は買いたい飲み物なので数にいれない。
        int amount = 0;                // 自動販売機で使えるお金の合計を保持する

        // 1、投入された硬貨の不正チェック
        // 2、投入金額の計算
        for (int i = 0; i < number; i++) {

            // 1枚ずつ硬貨を取り出す
            int coin = Integer.parseInt(args[i]);

            // 自動販売機で使えるお金かチェック
            switch (coin) {
                case 1:
                case 5:
                    // 1,5円玉は使えないので返却
                    System.out.println("[WARNING] " + coin + "円玉は返却します。");
                    break;
                case 10:
                case 50:
                case 100:
                case 500:
                    // 10,50,100,500円玉は使えるので投入金額に加算
                    amount += coin;
                    System.out.println("[INFO] ただいまの合計金額は " + amount + " 円です。");
                    break;
                default:
                    // それ以外の金額は機械の読み取りミスとして処理する
                    System.out.println("[WARNING] 金額をうまく読み取れませんでした。");
            }
        }

        // 1、購入できるドリンクかチェック
        // 2、購入金額が足りているかチェック
        // 3、購入を行う(お釣りの計算)
        String purchaseDrink = args[args.length - 1];

        // 購入できるドリンクかチェックする
        if (!drinks.containsKey(purchaseDrink)) {
            System.out.println("[WARNING] " + purchaseDrink + " は購入できないドリンクです。");
            System.out.println("[WARNING] お金を返却します。");
            System.exit(0); // 終了させる
        }

        // 金額チェックを行い、問題なければ購入する
        int price = drinks.get(purchaseDrink);
        if (amount >= price) {
            int change = amount - price;
            System.out.println("[INFO] " + purchaseDrink + " の購入に成功しました。");
            System.out.println("[INFO] " + change + " 円のお釣りです。");
        } else {
            System.out.println("[WARNING] お金が足りないため購入できません");
        }

        System.out.println("[DEBUG] ========== 購入処理：正常終了 ======");
    }
}
