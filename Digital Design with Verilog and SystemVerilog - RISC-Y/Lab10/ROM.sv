`timescale 1ns / 1ns

module ROM(input ROM_OE, input ROM_CS, input [4:0] ADDR, output reg [31:0]DATA);
    reg out;
    reg [31:0] ROM_MEMORY [0:31];    
    
    always@(ROM_OE, ROM_CS, ADDR)
    begin
    if(ROM_CS)
        out <= ROM_MEMORY[ADDR];
    
    if(ROM_OE)
        DATA <= out;
    else
        DATA <= 32'bz;
    end
endmodule
