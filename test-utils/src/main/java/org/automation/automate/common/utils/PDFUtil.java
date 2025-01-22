package org.automation.automate.common.utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.automation.automate.common.customexceptions.AutomationException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class PDFUtil {

    private static final ThreadLocal<PDFTextStripper> textStripperThreadLocal = ThreadLocal.withInitial(() -> {
        try {
            return new PDFTextStripper();
        } catch (IOException e) {
            throw new AutomationException("Failed to create PDFTextStripper", e);
        }
    });

    public static String extractTextFromFile(String filePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper pdfStripper = textStripperThreadLocal.get();
            return pdfStripper.getText(document);
        }
    }

    public static String extractTextFromBytes(byte[] pdfBytes) throws IOException {
        try (PDDocument document = PDDocument.load(pdfBytes)) {
            PDFTextStripper pdfStripper = textStripperThreadLocal.get();
            return pdfStripper.getText(document);
        }
    }

    public static String extractTextFromURL(String pdfUrl) throws IOException {
        try (InputStream inputStream = new URL(pdfUrl).openStream();
             PDDocument document = PDDocument.load(inputStream)) {
            PDFTextStripper pdfStripper = textStripperThreadLocal.get();
            return pdfStripper.getText(document);
        }
    }
}
