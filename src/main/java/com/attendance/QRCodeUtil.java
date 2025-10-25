package com.attendance;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Utility class for generating QR codes
 */
public class QRCodeUtil {
    
    private static final int QR_CODE_SIZE = 300;
    
    /**
     * Generate QR code image and save to file
     * @param data Data to encode in QR code
     * @param filePath Path to save the QR code image
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQRCode(String data, String filePath) throws WriterException, IOException {
        // Create QR code writer
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        
        // Set encoding hints
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        
        // Generate bit matrix
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, QR_CODE_SIZE, QR_CODE_SIZE, hints);
        
        // Create buffered image
        BufferedImage image = new BufferedImage(QR_CODE_SIZE, QR_CODE_SIZE, BufferedImage.TYPE_INT_RGB);
        
        // Fill image with QR code
        for (int x = 0; x < QR_CODE_SIZE; x++) {
            for (int y = 0; y < QR_CODE_SIZE; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        
        // Save image to file
        File qrFile = new File(filePath);
        ImageIO.write(image, "PNG", qrFile);
        System.out.println("QR Code saved to: " + qrFile.getAbsolutePath());
    }
    
    /**
     * Generate QR code for attendance
     * @param studentId Student ID
     * @param date Attendance date
     * @return QR code data string
     */
    public static String generateAttendanceQRData(int studentId, String date) {
        return "ATTENDANCE:" + studentId + ":" + date;
    }
}