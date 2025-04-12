    package com.example.prm392.utils;

    import com.example.prm392.config.VNPayConfig;
    import lombok.AccessLevel;
    import lombok.RequiredArgsConstructor;
    import lombok.experimental.FieldDefaults;
    import org.springframework.beans.factory.annotation.Value;

    import javax.crypto.Mac;
    import javax.crypto.spec.SecretKeySpec;
    import java.io.UnsupportedEncodingException;
    import java.net.URLEncoder;
    import java.nio.charset.StandardCharsets;
    import java.text.SimpleDateFormat;
    import java.util.*;
    import java.util.stream.Collectors;


    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public class VNPayUtil {

        public static  String createPaymentUrl(String cartId, Double amount) throws UnsupportedEncodingException {
            String vnp_Version = "2.1.0";
            String vnp_Command = "pay";
            String vnp_TmnCode = "OYNDTS2G";
            String vnp_CurrCode = "VND";
            String vnp_Amount = String.valueOf(amount.intValue()* 100); // Đơn vị của VNPay là VND * 100
            String vnp_OrderInfo = "Thanh toan don hang: " + "donhangthanhtoan";
            String vnp_ReturnUrl = "http://14.225.254.90:8080/api/cart/orderSucess";
            String vnp_TxnRef = cartId;
            String vnp_Locale = "vn";
            String vnp_OrderType = "other";
            String vnp_IpAddr = "127.0.0.1";
            String bank_code = "NCB";

            Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String vnp_CreateDate = formatter.format(cld.getTime());

            cld.add(Calendar.MINUTE, 15);
            String vnp_ExpireDate = formatter.format(cld.getTime());

            Map<String, String> params = new HashMap<>();
            params.put("vnp_Version", vnp_Version);
            params.put("vnp_Command", vnp_Command);
            params.put("vnp_TmnCode", vnp_TmnCode);
            params.put("vnp_Amount", vnp_Amount);
            params.put("vnp_BankCode", bank_code);
            params.put("vnp_CurrCode", vnp_CurrCode);
            params.put("vnp_TxnRef", vnp_TxnRef);
            params.put("vnp_OrderInfo", encodeValue(vnp_OrderInfo));
            params.put("vnp_OrderType", vnp_OrderType);
            params.put("vnp_Locale", vnp_Locale);
            params.put("vnp_ReturnUrl", vnp_ReturnUrl);
            params.put("vnp_IpAddr", vnp_IpAddr);
            params.put("vnp_CreateDate", vnp_CreateDate);
            params.put("vnp_ExpireDate", vnp_ExpireDate);

            List fieldNames = new ArrayList(params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();


            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();

            String secureHash = hmacSHA512("E4GH2LNQ3V11DMNUW4EI0M1YN9OUWIFB", hashData.toString());
            queryUrl += "&vnp_SecureHash=" + secureHash;
            return "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html?" + queryUrl;
        }

        private static String encodeValue(String value) {
            try {
                return URLEncoder.encode(value, StandardCharsets.UTF_8);
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi mã hóa URL", e);
            }
        }

        public static String hmacSHA512(final String key, final String data) {
            try {

                if (key == null || data == null) {
                    throw new NullPointerException();
                }
                final Mac hmac512 = Mac.getInstance("HmacSHA512");
                byte[] hmacKeyBytes = key.getBytes();
                final SecretKeySpec secretKey = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
                hmac512.init(secretKey);
                byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
                byte[] result = hmac512.doFinal(dataBytes);
                StringBuilder sb = new StringBuilder(2 * result.length);
                for (byte b : result) {
                    sb.append(String.format("%02x", b & 0xff));
                }
                return sb.toString();

            } catch (Exception ex) {
                return "";
            }
        }
    }
