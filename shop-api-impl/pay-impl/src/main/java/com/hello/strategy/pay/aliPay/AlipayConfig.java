package com.hello.strategy.pay.aliPay;

import org.springframework.beans.factory.annotation.Value;

public class AlipayConfig {

    // APP支付宝支付业务：app_id
    public static final String APP_ID = "2016101600697704";

    // 请求网关地址
    // 这是沙箱接口路径,正式路径为https://openapi.alipay.com/gateway.do
    public static final String SERVER_URL = "https://openapi.alipaydev.com/gateway.do";

    // 商户的私钥,您的PKCS8格式RSA2私钥。
    public static final String MERCHANT_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBNDdtHc2kwC7A+xqXTxLzB5u0GmYabyI6BiALCJTtl9PYhh0KKvvsvoexOTzfRpWouIXhrJC6xzZJPQz0pdD1SjJfT5uBMIhjsYj3QpTNpvwiQuH0l7G7KPHXPW+zGZNO3UKcIvexP2+BMSbsCW4Mt7fbi78yaubMKeuHb0iqCq7GnS2X1MMQVvSQNNgbA9mTekRIq8G4uX8lHktE/MFJuhOyst9jG9/aVT6V+8fTs8c3zJEnKQGSfPAs4ZsECD9Z3yhCuqop8CVU5v3tQOzPZhmzQ7wYlpmqDw1neDSUlFkryFPBnROMDEk+ulkATReKSB2eI01RsG5ZOny9Bym7AgMBAAECggEAZvpBOtzILIloOjNozDQf/EeSGAf6XRM6ilibbW/Xk06xcB/kQVhlFhn9eIkQNTzCx4jaXDX33IYuhEz+aHdIhlgusbx3So5uBldXvQr6Irb2ui/oe9PzuiI2LsEup+a4e+Dp2BweoszWiHeud+5EIirDhV5qczq03QCEUpulpLBYh40F4xMS/ycSuF3K4WwYzjvG6GaDtF81wH7sibAAOVRqt7wpGZ0k6Yw74augHrfdmgDTH6Qc6wpv9XUM6KDqVKCDYiRDl3hlPzsIoeeG7XU0A7Cp2JpIIQSGKR4C1JhXCVVHsvsas11bJgjfElA4BIkDv/G2S0o5b/TMHpV6YQKBgQDnxlUawlcUJk/J1wdmemhIvQJADveL1fCd0+i2HiZHRWhOaQdWa6xkiINjBT+zJnTd6LWaoUdgcdGvo6HMbspMXNtnSxvekR/E3ymtmVdftdu8q4Bueu+0bjsOcyalRYwsoKv4AEghXGSjKquXE2WE+5sY9DRh8IT9GSWhE9/NUwKBgQCOtV3gRkdDD6hfN07GRZnH2uL9jKHPwQgh02+bDK7tAghLVvyaiG0WYnK9zox+LKmf6XC+gwXdZQ0Z25dd6PjPhttPfFx+3inbLG27TX2+hFBCbFae0c7YUWs3UWDcbaDQMKzxfW3axs1YStTU8gPSAxb1EufwcTuSryg3esM8+QKBgECl6YcVPgS4yviZqc7DxeLEFaSYiCPRANSFDxv9POAUVAvK7+kDD4rbKQqtpB7sUxyylDUVVztng/1hvveuz26NWAVvxc5G3H2lYZKyetSPfUMa8y/dyo6hUIevRtBCnd600kLSkUv84XVBqaryFhcJtzE2rIXE+NM/+Gb9jCdRAoGADNADyasFouO4/jN9AxgkomjokFMcBAcUr9TH5GfTHLxd+kWpEEbHZIcaPihZPMrNv+m5kaE52ZLFBllM0cWk8Rzue8hylYFkyKTIhVSDwaMngY8wh7nWlF1a/vBzfdbp5Uc5f8T/I/FNZmvxh9zisuxMF1zHLjM8YgMP3UtMVCECgYAjj+Pqrhflp3aEn0nVukexJ0RxaHxRotLGV+3YzItxT+exnL0kjEekuQX6xsw1CCdF9kZfi/TukKTAQAEZbYXsIsxPZJdhddMz1tFQ1nuiInncx//sbN8j2EpENCN0mQsSko+E6abSRY6APjE89vIVWKkj1avIw2pl8B0RIxwS7w==";

    // 支付宝的公钥，去open.alipay.com对应应用下查看。
    public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0ZJljutzi49bHM7hkMD5/ACaO5y+hKZe/K5s0Yxy2myeJ61BzIKf8oorNA8GbaaSFIFowyHpzOyWdPOx7cnHFEXxDTKSO7x1vVhhYLQKbX04ZQbgGgXRTW9cUsWuH121bWxlRnbAskvg0itkIcNi5ujc3OkfiDtQnwYHutFIbj4zIBdkvQUWphZ/FJ6C5C7+Ezg8l1/pDjICLTJLWX93T3nR5HQTkRfhCxFhyw20v9qUTd+Yua1aVp7/NGoOSQMwmwSXkQuVrPKSb4OLGnavFT8Y/13kLupSLF5KSVt8j1MX5412FPjQUnlryMatgRQetyq3WSZsQ6wwLrjkfbBoJQIDAQAB";

    // 字符编码格式 目前支持 gbk 或 utf-8
    public static final String CHARSET = "UTF-8";

    // 签名方式，注意这里，如果步骤设置的是RSA则用RSA,如果按照扇面步骤做的话,选择RSA2
    public static final String SIGN_TYPE = "RSA2";

    // 返回格式
    public static final String FORMAT = "json";

    // 销售产品码，与支付宝签约的产品码名称。注：目前仅支持FAST_INSTANT_TRADE_PAY
    public static final String PRODUCT_CODE = "FAST_INSTANT_TRADE_PAY";

    // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问.如果只是测试使用,那么设置成自己项目启动后可以访问到的一个路径,作为支付宝发送通知的路径(有什么用暂时没发现)
    public static final String NOTIFY_URL = "http://meite.xiaomy.net/payCallBack/aliPayCallBack";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问.如果只是测试使用,那么设置成自己项目启动后可以访问到的一个路径.是支付正常完成后,会访问的路径.
    public static final String RETURN_URL = "http://localhost:8085/toPayYes";

    // 应用的公钥，无需修改该值
//    public static final String ali_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAgTQ3bR3NpMAuwPsal08S8webtBpmGm8iOgYgCwiU7ZfT2IYdCir77L6HsTk830aVqLiF4ayQusc2ST0M9KXQ9UoyX0+bgTCIY7GI90KUzab8IkLh9Jexuyjx1z1vsxmTTt1CnCL3sT9vgTEm7AluDLe324u/MmrmzCnrh29Iqgquxp0tl9TDEFb0kDTYGwPZk3pESKvBuLl/JR5LRPzBSboTsrLfYxvf2lU+lfvH07PHN8yRJykBknzwLOGbBAg/Wd8oQrqqKfAlVOb97UDsz2YZs0O8GJaZqg8NZ3g0lJRZK8hTwZ0TjAxJPrpZAE0XikgdniNNUbBuWTp8vQcpuwIDAQAB";


}
