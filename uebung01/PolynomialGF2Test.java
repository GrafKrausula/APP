public class PolynomialGF2Test {

        public static void main(String[] args) {

                System.out.println("============================== Test1 ===========================\n");
                boolean[] b = {true,false,true,true};                           // x^3 + x +1
                PolynomialGF2 irreduz = new PolynomialGF2(b);                   //irreduziebles polynom
                PolynomialGF2 generator=new PolynomialGF2();                    //generatorpolynom  (1)
                PolynomialGF2 tmp2;

                System.out.println("i | hash | x^i");
                for (int i = 0; i < 7; i++) {
                        tmp2=generator.mod(irreduz);
                        System.out.println(i+" |    "+tmp2.hashCode()+" | "+tmp2);
                        generator=generator.times(generator);                           // generator mit sich selbst multiplitieren, in dem fall reicht shiften da e$
                }
                System.out.println();
                System.out.println("============================== Test2 ============================\n");

                boolean[] b2 = {true,true};                                     // x + 1
                generator = new PolynomialGF2(b2);
                PolynomialGF2 generatorX = generator;
                boolean[] b3 = {true,false,false,false,true,true,false,true,true};
                irreduz = new PolynomialGF2(b3);                                // x^8 + x^4 + x^3 +x +1

                String hex;
                System.out.println("  |   0   1   2   3   4   5   6   7   8   9   a   b   c   d   e   f");
                System.out.println("--------------------------------------------------------------------");
                System.out.print("0 |  01  ");
                int row=0;
                for (int i = 1; i < 256; i++) {
                        tmp2=generatorX.mod(irreduz);
                        hex=Integer.toHexString(tmp2.hashCode());
                        if(hex.length()==1)hex= "0"+hex;                        // führende null hinzufügen
                        if(i%16==0) {
                                row++;                                          // alle 16 werte eine neue zeile
                                System.out.print("\n"+Integer.toHexString(row)+" |  ");
                        }
                        System.out.print(hex+"  ");
                        generatorX=generatorX.times(generator);                 // generatorX * generator sprich (x+1)^k
                }
                System.out.println();
        }

}
