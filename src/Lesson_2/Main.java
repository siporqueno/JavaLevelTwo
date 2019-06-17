package Lesson_2;

public class Main {
    static String[][] testArrayOne = {
            {"1", "1", "1", "1"},
            {"1", "???", "1", "1",},
            {"1", "1", "1"},
            {"1", "1", "1", "1",}
    };

    int a = +1;

    public static void main(String[] args) {
        try {
            System.out.println(new Main().sumOfStrArr(testArrayOne));
        } catch (MyArraySizeException eSize) {
            eSize.printStackTrace();
        } catch (MyArrayDataException eData) {
            System.out.printf("В ячейке %d %d нет числа\n", eData.getRowNo(), eData.getColNo());
            eData.printStackTrace();
        }

    }

    int sumOfStrArr(String[][] arrTwoDim) throws MyArraySizeException, MyArrayDataException {
        int sum = 0;
        if (arrTwoDim.length != 4) throw new MyArraySizeException();

        for (int i = 0; i < arrTwoDim.length; i++) {
            if (arrTwoDim[i].length != 4) throw new MyArraySizeException();
            for (int j = 0; j < arrTwoDim[i].length; j++) {
//                if (isParseableToInt(arrTwoDim[i][j])) throw new MyArrayDataException();
                try {
                    sum += Integer.parseInt(arrTwoDim[i][j]);

                } catch (NumberFormatException e) {
                    System.out.println("В строке НЕ число!");
                    throw new MyArrayDataException("В этой строке нет числа!!!", i, j);
                }
                }
            }

            return sum;
        }

        private boolean isParseableToInt (String s){
            s = s.trim();
            if (s.length() == 0) return false;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c < '0' || c > '9') return false;
            }
            return true;
        }
    }

    class MyArraySizeException extends ArrayIndexOutOfBoundsException {

    }

    class MyArrayDataException extends NumberFormatException {
        private int rowNo, colNo;

        public MyArrayDataException(String s, int rowNo, int colNo) {
            super(s);
            this.rowNo = rowNo;
            this.colNo = colNo;
        }

        public int getRowNo() {
            return rowNo;
        }

        public int getColNo() {
            return colNo;
        }
    }
