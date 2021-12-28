package filtroconvolucion;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;

public class FilteredImage extends BufferedImage {

    byte[] dataArray;
    byte[] dataArrayOriginal;
    int[][] kernel;
    int divisor;

    public FilteredImage(BufferedImage bi) {
        super(bi.getColorModel(), bi.getRaster(), bi.getColorModel().isAlphaPremultiplied(), null);
        
        this.setArraysData();
        this.kernel = new int[3][3];
        this.divisor = 1;
    }

    public void aplicarFiltro() {
        for (int fila = 1; fila < this.getHeight() - 1; fila++) {
            for (int col = 1; col < this.getWidth() - 1; col++) {
                int pixel = fila * this.getWidth() * 3 + col * 3;
                
                this.dataArray[pixel] = (byte) (this.getChannelValue(fila-1, col-1, 0));
                this.dataArray[pixel + 1] = (byte) (this.getChannelValue(fila - 1, col - 1, 1));
                this.dataArray[pixel + 2] = (byte) (this.getChannelValue(fila - 1, col - 1, 2));
                
                /* 
                this.dataArray[pixel] = (byte) (this.getChannelValue2(fila, col, 0));
                this.dataArray[pixel + 1] = (byte) (this.getChannelValue2(fila, col, 1));
                this.dataArray[pixel + 2] = (byte) (this.getChannelValue2(fila, col, 2));
                */
            }
        }
    }

    private int corregirValor(int valor) {
        if (valor < 0) {
            valor = 0;
        }
        if (valor > 255) {
            valor = 255;
        }

        return valor;
    }

    private int getChannelValue(int fila, int col, int channel) {
        int value = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int index = fila * this.getWidth() * 3 + col * 3;
                value += (Byte.toUnsignedInt(this.dataArrayOriginal[index + channel])) * this.kernel[i][j];
                col++;
            }
            col -= 3;
            fila++;
        }

        value = value / this.divisor;
        return corregirValor(value);
    }

    private int getChannelValue2(int fila, int col, int channel) {
        int value = 0;
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila - 1) * this.getWidth() * 3) + ((col - 1) * 3)) + channel])) * this.kernel[0][0];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila - 1) * this.getWidth() * 3) + (col * 3)) + channel])) * this.kernel[0][1];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila - 1) * this.getWidth() * 3) + ((col + 1) * 3)) + channel])) * this.kernel[0][2];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila) * this.getWidth() * 3) + ((col - 1) * 3)) + channel])) * this.kernel[1][0];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila) * this.getWidth() * 3) + (col * 3)) + channel])) * this.kernel[1][1];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila) * this.getWidth() * 3) + ((col + 1) * 3)) + channel])) * this.kernel[1][2];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila + 1) * this.getWidth() * 3) + ((col - 1) * 3)) + channel])) * this.kernel[2][0];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila + 1) * this.getWidth() * 3) + (col * 3)) + channel])) * this.kernel[2][1];
        value += (Byte.toUnsignedInt(this.dataArrayOriginal[(((fila + 1) * this.getWidth() * 3) + ((col + 1) * 3)) + channel])) * this.kernel[2][2];

        value = value / this.divisor;
        return corregirValor(value);
    }

    public void resetBytesArray() {
        System.arraycopy(this.dataArrayOriginal, 0, this.dataArray, 0, dataArray.length);
    }

    private void setArraysData() {
        Raster rDataImg = this.getRaster();

        if (rDataImg.getDataBuffer().getDataType() != DataBuffer.TYPE_BYTE) {
            System.out.println("ERROR: No se puede trabajar con esta imagen --> datos NO tipo BYTE");
        }

        // dataArray es el array de la imagen con el que trabajaremos
        this.dataArray = ((DataBufferByte) rDataImg.getDataBuffer()).getData();

        // dataArrayOriginal es una copia de los datos originales 
        this.dataArrayOriginal = new byte[this.dataArray.length];

        System.arraycopy(this.dataArray, 0, this.dataArrayOriginal, 0, dataArray.length);
    }

    public void setDivisor(int divisor) {
        this.divisor = divisor;
    }

    public void setKernel(int[][] kernel) {
        this.kernel = kernel;
    }
}
