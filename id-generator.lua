local incrKey = KEYS[1];
local step = ARGV[1];
local count;
count = tonumber(redis.call('INCRBY', incrKey, step));
local now = redis.call('TIME');

return {now[1], now[2], count}
