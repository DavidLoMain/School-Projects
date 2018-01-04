`timescale 1ns / 1ns

module portdirectionregister(CLK, RST_N, PDR_EN, I_DATA, O_DATA);
    input CLK, RST_N, PDR_EN;
    input I_DATA;
    output reg O_DATA;          

    always @(posedge CLK or negedge RST_N)                
        begin
            if(!RST_N)
                O_DATA = 1'b0;
            else if(PDR_EN) // If reset is high and enable is high
                O_DATA = I_DATA;     
            else                 
                O_DATA = O_DATA;            
        end
endmodule

