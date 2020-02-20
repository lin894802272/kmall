package com.zwl.order.config;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101300676463";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDk/n1UEgPvXkPMkAAhRh+hCXJzOro6xxl6zUAgrzl748VS2tC+ZnqP4HiUy75sbHUL+TQJ46XV1l5Ix0oi1xi8g0bLzWNB3byIQF1wwyA6IpA3lBuMcqp5pRqulKYewVZ87l4NyyjlrS7abUjhEvOgLW7RQ8jM0ByMSPDo3Ke/nvGg4+A7/pyO5F+KhCZbPrWg0sOj1tN7Z1F+eRtyGj6uIzvj2491pbJxwEsbFk/keoERv2tZeHjBiNCQ2+QsqBf34fw96KCXoVsKz/RKe1ZNWNIH/x4vFuVVVbWUn2q03wISKltxBYerMXcGvDr/Q7uXpNk9pG8Ft+v/YqpnkUq3AgMBAAECggEBALLtRvBRw1FdzowgWHmnWT9zKkJvOOSEfZw+lET1pWzwZFaBM2oSmOGi+5UIPhpqBGr+pXK0tfeIMNOdJIhkmadyy/ai3VOx9oZgBKL/VEcYiqdiysPLNPkn0NlovQR6/SFQ/5y3iZUs5Nc3XSLAtCwMU/KfjF68nhHfTJ4nC8vk43jYYuAdHlVNxkhKJXC3nfFQxoxarGN05ve5itiPqm7FhXmWzV+qEjjoutDzFw910GRZglpPCWaL6lnRogOlJFXjY3e5Uq7+NRxjJNZpBRJPnqNopjk5sxVTXUmnrwN3b9NcXtGKmuDNfl0Nv+6G1Z0wjznZjQ1HwCxZ+iNIaMECgYEA8mAe8Hv39jwztTRM1VCrhwjDRnou0gpknEpicjF8CPFNDrqfrkVCHB1qeSI7Wplptu8HsI2B/64WUXR0C+HjV5y3dj5d4KpaZvXGpI/sZ051r9rFu6ffgM4YHxpl4qtQGP/s96Pi22nx5RBekma6RDQYl8s+YMhjcRazZedkpm0CgYEA8d3OMkwViP7XuKoKdHYiAaLnvF4AghIZVQkNfV5mmoea7KfVLk38tr/dGbZlap9En0qfOFFqPH9dIqgft40An0Qhjtx7x68fVtBTaeq4tar/NFGVAJ02nHwClX4USzsogo2juRQTxenwqqpv6SoUlXvpx2n0wkHdgu1aaIx6zzMCgYAPZlQQ64+BsamHZd9L3mhHmCLwI2s+JTLnS0KeBs7espY2zVy/8d6VcrkFIUusIfs5iE5wxUzxNdptKn0FFEEhm+98L9I/KBO+XvdUFWDr32xS1A+kMLJeuLmHoGHA2SmprPkdcuhDEKQx7axUmPpUwwqc+mjoNVM8KDkVzHH0sQKBgQDlvUU+G20dOsxXuRTDOO7mVQH/t96Nrnlnf3jTy9Z0FDjBcE4bdLKGOErhQJKmq3A4Sl5g1Vyu6F/kKoQuCn47yjCCDcZ5j0uaX7nL0svuq+VLoe6BrdOQ4m5l52OJbkD1eWq5w2tgw+z5f3lbpb/1mcUeyPNXjc7FrYUB5+ZDZwKBgGOAWIyzBNzJEIDJIZhscyXrka5ZasUcTQkHCwAk655ROBmt9Fnyijkvmco7Maoxq0jvA+mwaRFfC4kLoB9pvAiVkjqO9x1DbLzVcyrmsZyl9DZkrh3X4wggqbO4HAebcptx2ZFzmQycu2ckHAbqBcja39DysWuazXe2TbtECtbW+gbAL3dU+7GayM/ZqNEW8ZHATZU+hLk+B67d8Yf+nYdIvTdx0dxXbzX/fXy7T5cZ9R24LweXVy0X6fIArgpOMi42c8H+/+OSHE0peZIcAcViEsqneZkg04H6dXhl8mVBevxfl+b9hLf4zTZ0rHaniIkGwf/YsuNYVe0zoWTtz8QxBv45OBRURhRyZGYiW6kL5R9C0JhdeZi72+KbsGjUiVqok3fr4nSxYNzqFNpC+faSK063zv/EO/1py1/LIFNrsf5JTlXG58/A7W/hoYTnrwByjnkYRuoWlbHqi5f92sRZznoTFP4OtNz6t3AgMBAAECggEARRnnc/hDhcPcedrkPmj8k+DOllY26civT+RQTO5w3p/EgqmOtJzwIDM1ruQpUEbDut1rVWVOY52VdmtgiA9kwiKdnH570WNsbAlG/hAL3zpVeRmv1onZTAk3l033HlY+4e1om/WWFrOFfzDf7TDHNp+uFaGbAo0vULY+GQQhU7EUoxraAqmqibUnB2g9jjfN2gL/D8wrF4RSNu/XsVWvnL5MHw1Ro2TD+aOwx0/rVbIdRnHgHdOCKPTU9CexRtcp3qSmXBz/RLFLv1Dh9OimkKmqFf39KEaUGos6ER1rz3Qjl3mZ4YtROcCV7NpN5JwKEpDrHrQjdCc3XCn8m67yGQKBgQDksi7r9nGN44quBp6eTiNhzM9mtkG8l6DqQlOO9Yk/Y8r5tmmnWq/zIltD7QZWRFsc9q9WM5aphoIx28oxYgGtADO29/I39X51msupc1q10Edv0BigQt58Rf6F8rf49KpeuF6OzlsrkllnZhtcboZwtE958Jff/NUH/RwCpmuywwKBgQDObrlKe6tJfgNVHdbr1b3i4WtEv2tpIqWdi/DLj6aqbXrmyC9hbsV31QKKormav6Xov9MJZMK9AOG3OV4/VFJ3f+u+YI/url17FSI+uurOP/6ECxA/uPJTsruGxgFGMY421BEPEt3dJqoRx8tLToEcS2ulobqUX1Qa3DnbEupxPQKBgEQVxCBdu0rJvkS3Fcv9aU05g1mJ0yJJZg+39VZvRRAJLqmPI/894XoZ9V/Qlf1i1llWG7cnHqiTsfkT/SsciXEuyTaktIjyXla24gEtpYjRXCMuqdY0cjY/RFoue5TzijEu5H/RcZUUOeetn2GySfYNyEd+WEjH5cg+E3O6hf+vAoGACOeX0JGzGtF3vVUzLysiLMZChU2oiXl4IWqm8XD55IIiehAKky5fEEM8/9PzI6y+sGKN7OrOx65hDjFIUn0Frafz+Ftb+hTFbYjE2GXhuRHjnbiIc+c7dMbR9gdWiLucgdr1/Wt37JE9ZtTyfQYe5aSPOS7XyIywKRR03nFbcZUCgYEAlvsvkLTWa/ABM7ZgNuLB1AafxVwSu41BS9aswqcSsaVqKKXipHJ48Gvzw6aDy2Pz7JMla86X778MGE6a4ijXNUYnaY4ZbWrY53aF8MdQy4tnC6lV3rFfKn3BsciUG5yGIAo8YbxTXLZaq05iqrGYDYPE68e8vxkhmox5oqK7MGg=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjWAflQu0vpBx2M+FK+3B2VuK7/mh6OSnf+fNPh3Rnj3RiNKf/kM5neVC/94yA/Ze4c2EOhca5G7waaT3WI94B2Wao2Pb5O9qNqV+PUvxBTZIAa5r0pcVam4oe9vNg11UNiHvpUMbLj10q9etsMbUklTLklL/ILB0jB3DIB6tFPx+n9E44+fZwgERzun/HoqBcC5IIAphSWIkgavpfZR4MLeuIUTOiMYa53PnAdl9p7wQUmvgBy4TiKEmxxtUYAsNRaIbJF3EXyLwo55kj7oF09ZP0l1+bcVnwcFDHLEm6FFg3wc0G/1illwBgNYSRL6ZnRXYwEvbC3vYI9N//18aOwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8086/order/my-orders-page";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关，这是沙箱的网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

